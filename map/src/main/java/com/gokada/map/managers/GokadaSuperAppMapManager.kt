package com.gokada.map.managers

import android.animation.ValueAnimator
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.location.Location
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.akexorcist.googledirection.DirectionCallback
import com.akexorcist.googledirection.GoogleDirection
import com.akexorcist.googledirection.model.Direction
import com.akexorcist.googledirection.util.DirectionConverter
import com.directions.route.*
import com.gokada.core.utils.BaseAppLog
import com.gokada.map.BuildConfig
import com.gokada.map.R
import com.gokada.map.utils.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*
import com.google.maps.android.SphericalUtil
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import kotlin.properties.Delegates


class GokadaSuperAppMapManager constructor(
    private val map: GoogleMap,
    private val locationManager: GokadaSuperAppLocationManager<*>
) : GoogleMap.OnCameraMoveStartedListener {

    var pickupLocation by Delegates.observable<Location?>(null) { _, _, newValue ->
        pickupAddress = locationManager.decodeLocation(newValue!!)
    }
    var dropoffLocation by Delegates.observable<Location?>(null) { _, _, newValue ->
        dropOffAddress = locationManager.decodeLocation(newValue!!)
    }

    lateinit var pickupAddress: String
    lateinit var dropOffAddress: String

    lateinit var tripPolyline: Polyline

    var isUserSelectDestination = false
    var userSelectPickUpLocation = true

    private lateinit var pickupLocationMarker: Marker
    private lateinit var riderLocationMarker: Marker
    private lateinit var dropOffLocationMarker: Marker
    private var pilotLocationMarker: Marker? = null
    private var polylines: MutableList<Polyline> = ArrayList()

    private val disposable = CompositeDisposable()

    init {
        dropoffLocation = Location("")

        disposable.add(
            locationManager.locationPublisher.doOnNext {
                if (pickupLocation == null) {
                    /**
                     * The Offset is for map animation.
                     * Ask for someone called Uche in the UI team.*/

                    val location = LatLng(it.latitude - 0.0002, it.longitude - 0.0002)
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 19.0f),
                        object : GokadaSuperAppMapCancelableCallback() {
                            override fun onFinish() {
                                map.animateCamera(
                                    CameraUpdateFactory.newLatLngZoom(
                                        it.latLng,
                                        19.0f
                                    )
                                )
                                val markerOptions = markerOptions {
                                    position(it.latLng)
                                }
                                pickupLocationMarker = map.addMarker(markerOptions)
                            }
                        }
                    )
                } else {
                    if (!::pickupLocationMarker.isInitialized) {
                        val markerOptions = markerOptions {
                            position(it.latLng)
                        }
                        pickupLocationMarker = map.addMarker(markerOptions)
                    }
                    animateMarkerToNewPosition(pickupLocationMarker, it)
                }
                pickupLocation = it
            }.subscribe()
        )
    }

    private fun animateMarkerToNewPosition(marker: Marker, newCoordinates: Location) {
        val startValues = doubleArrayOf(marker.position.latitude, marker.position.longitude)
        val endValues = doubleArrayOf(newCoordinates.latitude, newCoordinates.longitude)
        val valueAnimator = ValueAnimator.ofObject(DoubleArrayEvaluator(), startValues, endValues)
        valueAnimator.apply {
            duration = 1000
            interpolator = DecelerateInterpolator()
        }
        valueAnimator.addUpdateListener {
            val animatedValue = it.animatedValue as DoubleArray
            marker.position = LatLng(animatedValue[0], animatedValue[1])
        }
        valueAnimator.start()
    }

    override fun onCameraMoveStarted(reason: Int) {
        if (reason == GoogleMap.OnCameraMoveStartedListener.REASON_GESTURE) {
            if (isUserSelectDestination) {
                if (userSelectPickUpLocation) {
                    pickupLocation = map.cameraPosition!!.target!!.location
                } else {
                    dropoffLocation = map.cameraPosition!!.target!!.location
                }
            }
        }
    }

    private fun navigateToCurrentLocation() {
        animateMarkerToNewPosition(pickupLocationMarker, pickupLocation!!)
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(pickupLocationMarker.position, 19.0f))
    }

    fun drawPolylineFromPickupToDropoff() {
        map.clear()
        val pickupMarkerOptions = markerOptions {
            val pickupView =
                locationManager.activity.layoutInflater.inflate(R.layout.layout_marker_view, null)
            with(pickupView) {
                findViewById<TextView>(R.id.marker_address).text = pickupAddress
            }
            val pickupBitmap = createBitmapFromView(locationManager.activity, pickupView)
            position(pickupLocation!!.latLng)
            icon(BitmapDescriptorFactory.fromBitmap(pickupBitmap))
        }
        val dropoffMarkerOptions = markerOptions {
            val dropoffView =
                locationManager.activity.layoutInflater.inflate(R.layout.layout_marker_view, null)
            with(dropoffView) {
                findViewById<TextView>(R.id.marker_address).text = dropOffAddress
            }
            val pickupBitmap = createBitmapFromView(locationManager.activity, dropoffView)
            position(dropoffLocation!!.latLng)
            icon(BitmapDescriptorFactory.fromBitmap(pickupBitmap))
        }
        pickupLocationMarker = map.addMarker(pickupMarkerOptions)
        dropOffLocationMarker = map.addMarker(dropoffMarkerOptions)
//        val options = polyLineOptions {
//            add(pickupLocation!!.latLng, dropoffLocation!!.latLng)
//            geodesic(true)
//        }
//        tripPolyline = map.addPolyline(options)
//        showCurvedPolyline()
//        setCameraWithCoordinationBounds()
//        drawRoute(pickupLocation!!, dropoffLocation!!)
        drawRoute(pickupLocation!!, dropoffLocation!!)
    }

    fun drawRouteWithNewLibrary(
        startLocation: Location,
        endLocation: Location, @DrawableRes startIcon: Int, @DrawableRes endIcon: Int
    ) {
        map.clear()
        val start = LatLng(startLocation.latitude, startLocation.longitude)
        val end = LatLng(endLocation.latitude, endLocation.longitude)
        Routing.Builder().apply {
            travelMode(AbstractRouting.TravelMode.DRIVING)
            key(BuildConfig.GOOGLE_MAPS_API)
            alternativeRoutes(false)
            waypoints(start, end)
            withListener(object : RoutingListener {
                override fun onRoutingCancelled() {}

                override fun onRoutingStart() {}

                override fun onRoutingFailure(p0: RouteException?) {
                    BaseAppLog.e("onRoutingFailure ${p0?.message}")
                }

                override fun onRoutingSuccess(route: ArrayList<Route>, p1: Int) {
                    if (polylines.size > 0) {
                        for (poly in polylines) {
                            poly.remove()
                        }
                    }

                    polylines = ArrayList()
                    //add route(s) to the map.
                    for (i in route.indices) {
                        val polyOptions = PolylineOptions()
                        polyOptions.color(Color.parseColor("#00C795"))
                        polyOptions.width((10 + i * 3).toFloat())
                        polyOptions.addAll(route[i].points)
                        val polyline = map.addPolyline(polyOptions)
                        polylines.add(polyline)
                    }

                    val pickupMarkerOptions = markerOptions {
                        position(start)
                        icon(
                            createBitmapDescriptorFromVector(
                                locationManager.activity,
                                startIcon
                            )
                        )
                    }
                    val pilotMarkerOptions = markerOptions {
                        position(end)
                        icon(
                            createBitmapDescriptorFromVector(
                                locationManager.activity,
                                endIcon
                            )
                        )
                    }

                    riderLocationMarker = map.addMarker(pickupMarkerOptions)
                    pilotLocationMarker = map.addMarker(pilotMarkerOptions)
                    zoomRoute(route[0].points)
                }
            })
        }.build().execute()
    }

    private fun zoomRoute(lstLatLngRoute: List<LatLng>) {
        if (lstLatLngRoute.isEmpty()) return

        val boundsBuilder = LatLngBounds.Builder()
        for (latLngPoint in lstLatLngRoute)
            boundsBuilder.include(latLngPoint)

        val routePadding = 120
        val latLngBounds = boundsBuilder.build()

        map.animateCamera(
            CameraUpdateFactory.newLatLngBounds(latLngBounds, routePadding),
            600,
            null
        )
    }

    private fun drawRoute(fromLocation: Location, toLocation: Location) {
        GoogleDirection.withServerKey(BuildConfig.GOOGLE_MAPS_API)
            .from(fromLocation.latLng)
            .to(toLocation.latLng)
            .execute(object : DirectionCallback {
                override fun onDirectionSuccess(direction: Direction?) {
                    if (direction!!.isOK) {
                        val route = direction.routeList[0]
//                        dropOffLocationMarker = map.addMarker(
//                            markerOptions {
//                                position(dropoffLocation!!.latLng)
//                            }
//                        )
                        val directions = route.legList[0].directionPoint
                        tripPolyline = map.addPolyline(
                            DirectionConverter.createPolyline(
                                locationManager.activity,
                                directions,
                                5,
                                ContextCompat.getColor(
                                    locationManager.activity,
                                    R.color.colorPrimary
                                )
                            )
                        )
                        zoomRoute(route.legList[0].directionPoint)
                    }
                }

                override fun onDirectionFailure(t: Throwable?) {
                    BaseAppLog.logException(t!!)
                }
            })
    }

    private fun addCircleToMap(
        latitude: Double,
        longitude: Double,
        radius: Double = 200.0,
        fillColorHex: String = "#F4DFDF",
        strokeColorHex: String = "#FFFFFF",
        strokeWidth: Float = 2.0f
    ) {
        val circleOptions = CircleOptions()
            .center(LatLng(latitude, longitude))
            .radius(radius)
            .fillColor(Color.parseColor(fillColorHex))
            .strokeWidth(strokeWidth)
            .strokeColor(Color.parseColor(strokeColorHex))

        map.addCircle(circleOptions)
    }

    fun getRideLocation(callback: (Location) -> Unit) {
        disposable.add(
            locationManager.rideLocationPublisher.doOnNext {
                callback.invoke(it)
            }.subscribe()
        )
    }

    private fun createBitmapFromView(context: Context, view: View): Bitmap {
        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        view.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels)
        view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels)
        view.buildDrawingCache()
        val bitmap = Bitmap.createBitmap(
            view.measuredWidth,
            view.measuredHeight,
            Bitmap.Config.ARGB_8888
        )

        val canvas = Canvas(bitmap)
        view.draw(canvas)

        return bitmap
    }

    private fun createBitmapDescriptorFromVector(
        context: Context,
        vectorDrawableResourceId: Int
    ): BitmapDescriptor {

//        val background: Drawable? = ContextCompat.getDrawable(context, R.drawable.ic_map_pin_filled_blue_48dp)
//        background?.setBounds(0, 0, background.intrinsicWidth, background.intrinsicHeight)
        val vectorDrawable: Drawable =
            ContextCompat.getDrawable(context, vectorDrawableResourceId)!!
//        vectorDrawable?.setBounds(40, 20, 40 + vectorDrawable.intrinsicWidth, 20 + vectorDrawable.intrinsicHeight)
        val left = (vectorDrawable.intrinsicWidth - vectorDrawable.intrinsicWidth) / 2
        val top = (vectorDrawable.intrinsicHeight - vectorDrawable.intrinsicHeight) / 3
        vectorDrawable.setBounds(
            left,
            top,
            left + vectorDrawable.intrinsicWidth,
            top + vectorDrawable.intrinsicHeight
        )
        val bitmap: Bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
//        background.draw(canvas)
        vectorDrawable.draw(canvas)

        return BitmapDescriptorFactory.fromBitmap(bitmap)

//        val left = (background.intrinsicWidth - vectorDrawable.intrinsicWidth) / 2
//        val top = (background.intrinsicHeight - vectorDrawable.intrinsicHeight) / 3
//        vectorDrawable.setBounds(left, top, left + vectorDrawable.intrinsicWidth, top + vectorDrawable.intrinsicHeight)

    }

    private fun setCameraWithCoordinationBounds() {
//        val southwest = route.bound.southwestCoordination.coordination
//        val northeast = route.bound.northeastCoordination.coordination
//        val bounds = LatLngBounds(southwest, northeast)
        val bounds = LatLngBounds.Builder().include(pickupLocation!!.latLng)
            .include(dropoffLocation!!.latLng).build()
        val width = locationManager.activity.resources.displayMetrics.widthPixels
        val height = locationManager.activity.resources.displayMetrics.heightPixels
        val padding = height * 0.05
        map.animateCamera(
            CameraUpdateFactory.newLatLngBounds(
                bounds,
                width,
                height,
                padding.toInt()
            )
        )
    }

    private fun showCurvedPolyline(k: Double = 0.2) {
        //Calculate distance and heading between two points
        val d =
            SphericalUtil.computeDistanceBetween(pickupLocation!!.latLng, dropoffLocation!!.latLng)
        val h = SphericalUtil.computeHeading(pickupLocation!!.latLng, dropoffLocation!!.latLng)

        //Midpoint position
        val p = SphericalUtil.computeOffset(pickupLocation!!.latLng, d * 0.5, h)

        //Apply some mathematics to calculate position of the circle center
        val x = (1 - k * k) * d * 0.5 / (2 * k)
        val r = (1 + k * k) * d * 0.5 / (2 * k)

        val c = SphericalUtil.computeOffset(p, x, h + 90.0)

        //Polyline options
        val options = PolylineOptions()
        val pattern = listOf(Dash(30f), Gap(20f))

        //Calculate heading between circle center and two points
        val h1 = SphericalUtil.computeHeading(c, pickupLocation!!.latLng)
        val h2 = SphericalUtil.computeHeading(c, dropoffLocation!!.latLng)

        //Calculate positions of points on circle border and add them to polyline options
        val numpoints = 100
        val step = (h2 - h1) / numpoints

        for (i in 0 until numpoints) {
            val pi = SphericalUtil.computeOffset(c, r, h1 + i * step)
            options.add(pi)
        }

        //Draw polyline
        tripPolyline = map.addPolyline(
            options.width(10f).color(
                ContextCompat.getColor(
                    locationManager.activity,
                    R.color.colorPrimary
                )
            ).geodesic(false).pattern(pattern)
        )
    }

    fun reset() {
        pickupLocationMarker.remove()
        dropOffLocationMarker.remove()
        tripPolyline.remove()
        map.clear()
        val markerOptions = markerOptions {
            position(pickupLocation!!.latLng)
        }
        pickupLocationMarker = map.addMarker(markerOptions)
        navigateToCurrentLocation()
    }

    fun cleanup() {
        locationManager.cleanup()
        disposable.dispose()
    }
}
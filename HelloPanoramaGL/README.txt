PanoramaGL Library
==================
Version 0.2 beta (September/2013)
Copyright (c) 2010 Javier Baez <javbaezga@gmail.com>

1. Features
===========
- SDK 2.0 to 4.2.2.
- Architectures ARM, x86 and MIPS.
- OpenGL ES 1.0 and 1.1.
- Support for spherical, cubic and cylindrical panoramic images.
- Scrolling and continuous scrolling.
- Inertia to stop continuous scrolling.
- Zoom in and zoom out (moving two fingers on the screen).
- Reset (placing three fingers on the screen or shaking the device).
- Scrolling left to right and from top to bottom using the accelerometer.
- Sensorial rotation (Only compatible for devices with Gyroscope or Accelerometer and Magnetometer).
- Full control of camera including field of view, zoom, rotation, rotation range, animations, etc.
- Hotspots with commands.
- Simple JSON protocol.
- Creation of virtual tours using the JSON protocol or with programming.
- Transitions between panoramas.
- Support for events.

2. Licensing
============
* PanoramaGL is open source licensed under the Apache License version 2.0.
* Please, do not forget to put the credits in your application :).

2.1. Third-party code used by PanoramaGL
========================================
* glues: OpenGL ES 1.0 CM port of part of GLU by Mike Gorchak <mike@malva.ua>. Copyright (c) 1991-2000 Silicon Graphics, licensed under SGI FREE SOFTWARE LICENSE B version 2.0.
* Matrix, MatrixGrabber, MatrixStack and MatrixTrackingGL classes: Copyright (c) 2007 The Android Open Source Project, licensed under Apache License version 2.0.
* HttpClient 3.1: Copyright (c) Apache Software Foundation, licensed under Apache License version 2.0.
* EasySSLSocketFactory and EasyX509TrustManager classes: Copyright (c) Apache Software Foundation, licensed under Apache License version 2.0.
* PLVector3 and PLIntersection classes: Port from C++ to Java of "Demonstration of a line mesh intersection test" example by Jonathan Kreuzer.
* PLTokenizer class: Class based in the article "Writing a Parser in Java: The Tokenizer" by Cogito Learning.

2.2. Panoramic images used by HelloPanoramaGL example
=====================================================
* quito1, quito2 and quito3 images: Copyright (c) 2013 Geovanny Raura <georaura@gmail.com>.

3. Requirements
===============
* Android 2.0 or higher.
* OpenGL 1.0 or higher.
* Some functionalities need the Accelerometer, Magnetometer and/or Gyroscope.
 
4. How to import PanoramaGL library?
====================================

4.1. From source code
=====================
a. Download PanoramaGL_0.2-beta.zip or download the source code from repository.
b. If you download the zip file then decompress the file.
c. Import PanoramaGL project with Eclipse:
    - Go to File->Import menu.
    - Select "Existing Projects into Workspace" and click on "Next" button.
    - Click on "Browse" button and select PanoramaGL project folder.
    - Click on "Finish" button.
d. Right click on your project and select "Properties" option.
e. Select on left panel "Android" option.
f. In right panel go to "Library" section and click on "Add..." button.
g. Select "PanoramaGL" library and click on "OK" button.
h. Accept the changes selecting the "OK" button on right bottom corner in properties window.

4.2. From the compiled files
============================
a. Download libglues_0.2-beta.zip.
b. Decompress the zip file and copy "libs" folder in your project.
c. Download PanoramaGL_0.2-beta.jar.
d. Copy jar file in "libs" folder in your project.
e. Import jar file in your project:
    - Right click on your project and select "Properties" option.
    - Select on left panel "Java Build Path" option.
    - Select "Libraries" tabulator.
    - Click on "Add JARs..." button.
    - Select "libs/PanoramaGL_0.2-beta.jar" file from your project.
    - Click on "OK" button.
    - Accept the changes selecting the "OK" button on right bottom corner in properties window.

5. How to use PanoramaGL in your application?
=============================================
a. Import the library as described on previous literal.
b. Import a spherical image (e.g. spherical_pano.jpg) in "res/raw" folder.
c. In the Activity class that you need to make a panoramic viewer, do the next changes:

	- Inherit from PLView class
		public class YourActivity extends PLView
	- Within the onCreate method, load the panoramic image
        @Override
        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            PLSpherical2Panorama panorama = new PLSpherical2Panorama();
            panorama.setImage(new PLImage(PLUtils.getBitmap(this, R.raw.spherical_pano), false));
            this.setPanorama(panorama);
		}

Note: You can load panoramic images from other methods or events if you need it.

6. Simple JSON Protocol
=======================
You can use JSON protocol to load panoramas and to create virtual tours.

6.1. Source code
================

6.1.1. From the resources
=========================
this.load(new PLJSONLoader("res://raw/json_spherical"));

Note: For this code, you need to have a file named "json_spherical.data" in "res/raw" folder in your application.

6.1.2. From the file system
===========================
this.load(new PLJSONLoader("file:///sdcard/files/json_spherical.data"));

Note: For this code, you need to have a file named "json_spherical.data" in "/sdcard/files" folder on the Android device or emulator.

6.1.3. From a Web server
========================
this.load(new PLJSONLoader("http://mydomain/files/json_spherical.data"));

Note: For this code, you need to have a file named "json_spherical.data" in "http://mydomain/files" Web server path.

6.2. JSON Protocol
==================

{
    "urlBase": "file:///sdcard/files",		//URL base where the files are
											//The options are: http://, https://, res:// for application resources and file:// for the file system
    "type": "spherical",					//Panorama type: [spherical, spherical2, cubic, cylindrical]
    "keep": "none",							//Keeps the current settings [none, reset, scrolling, inertia, accelerometer, sensorialRotation, all] <Optional>
											//By default the value is none and you can use the options like a mask e.g. all|~scrolling that meaning keep all except the scrolling options
    "imageColorFormat": "RGBA8888",			//Color format to be used for all images [RGBA8888, RGB565, RGBA4444] <Optional>
    "height": 3.0,							//Sets the panorama's height only for cylindrical panorama <Optional>
    "divisions":							//Divisions section only for spherical, spherical2 and cylindrical panoramas <Optional>
    {
    	"preview": 50,						//Number of divisions for the preview panorama <Optional>
    	"panorama": 50						//Number of divisions for the panorama <Optional>
    },
    "reset":								//Reset section <Optional>
    {
    	"enabled": true,					//Enable reset feature [true, false] <Optional>
    	"numberOfTouches": 3,				//Number of touches to reset <Optional>
    	"shake":							//Shake reset section <Optional>
    	{
    		"enabled": true,				//Enable shake reset [true, false] <Optional>
    		"threshold": 1300				//Shake threshold <Optional>
    	}
    },
    "scrolling":							//Scrolling section <Optional>
	{
		"enabled": true,					//Enable scrolling feature [true, false] <Optional>
		"minDistanceToEnableScrolling": 30	//Minimum distance to enable scrolling in pixels <Optional>
	},
    "inertia":								//Inertia section <Optional>
    {
        "enabled": false,					//Enable inertia feature [true, false] <Optional>
        "interval": 3						//Inertia's interval in seconds <Optional>
    },
    "accelerometer":						//Accelerometer section <Optional>
    {
        "enabled": false,					//Enable the accelerometer feature [true, false] <Optional>
        "interval": 0.033,					//Update interval of accelerometer (this value must be calculated as 1/frequency) <Optional>
        "sensitivity": 10.0,				//Sensitivity of the accelerometer <Optional>
        "leftRightEnabled": true,			//Enable the movement (left/right) <Optional>
        "upDownEnabled": false				//Enable the movement (up/down) <Optional>
    },
    "sensorialRotation": false,				//Automatic rotation using sensors [true, false] <Optional>
    "images":								//Panoramic images section
											//Image properties can be a name e.g. preview.jpg, preview or an URL e.g. http://mydomain/files/preview.jpg, file:///sdcard/files/preview.jpg, res://raw/preview
											//if an image property only have a name, the real path will be the urlBase + image name
    {
    	"preload": true,					//Preload the images [true, false] <Optional>. Note: For HTTP is better to use preview option and preload option equal to false
        "preview": "preview.jpg",			//Preview image name or URL <Optional>
        "image": "pano.jpg",				//Panoramic image name or URL only for spherical, spherical2 and cylindrical panoramas
        "front": "front.jpg",				//Front image name or URL only for cubic panorama
        "back": "back.jpg",					//Back image name or URL only for cubic panorama
        "left": "left.jpg",					//Left image name or URL only for cubic panorama
        "right": "right.jpg",				//Right image name or URL only for cubic panorama
        "up": "up.jpg",						//Up image name or URL only for cubic panorama
        "down": "down.jpg"					//Down image name or URL only for cubic panorama
    },
    "camera":								//Camera section <Optional>
    {
    	"keep":	"none",						//Keeps the current camera settings [none, atvMin, atvMax, atvRange, athMin, athMax, athRange, reverseRotation, rotationSensitivity, vLookAt, hLookAt, rotation, zoomLevels, fovMin, fovMax, fovRange, fovSensitivity, fov, allRotation, allZoom, all] <Optional>
											//By default the value is none and you can use the options like a mask e.g. all|~atvRange that meaning keep all except the atvRange options
    	"atvMin": -90.0,					//Minimum vertical rotation in degrees [-90.0, 90.0] (down) <Optional>
        "atvMax": 90.0,						//Maximum vertical rotation in degrees [-90.0, 90.0] (up) <Optional>
        "athMin": -180.0,					//Minimum horizontal rotation in degrees [-180.0, 180.0] (left) <Optional>
        "athMax": 180.0,					//Maximum horizontal rotation in degrees [-180.0, 180.0] (right) <Optional>
        "reverseRotation": true,			//Reverse rotation [true, false] <Optional>
        "rotationSensitivity": 30.0,		//Rotation sensitivity in pixels [1.0, 180.0] <Optional>
        "vLookAt": 0.0,						//Initial vertical rotation in degrees [-90.0, 90.0] <Optional>
        "hLookAt": 0.0,						//Initial horizontal rotation in degrees [-180.0, 180.0] <Optional>
        "zoomLevels": 2,					//Zoom levels for zoom in and zoom out [1, ...] <Optional>
        "fovMin": 30.0,						//Minimum field of view in degrees [0.01, 179.0] <Optional>
        "fovMax": 90.0,						//Maximum field of view in degrees [0.01, 179.0] <Optional>
        "fovSensitivity": 30.0,				//Field of view sensitivity in pixels [1.0, 100.0] <Optional>
        "fov": 90.0,						//Initial field of view in degrees [0.01, 179.0] <Optional> Note: see notes at the end of literal
        "fovFactor": 1.0,					//Field of view factor [0.0, 1.0] <Optional> Note: see notes at the end of literal
        "zoomFactor": 0.0,					//Zoom factor [0.0, 1.0] <Optional> Note: see notes at the end of literal
        "zoomLevel": 0						//Zoom level [0, zoomLevels] <Optional> Note: see notes at the end of literal
    },
    "hotspots":								//Hotspots section (this section is an array of hotspots) <Optional>
    [
		{
			"id": 1,						//Identifier (Integer number) <Optional>
			"atv": 0.0,						//Vertical position in degrees [-90.0, 90.0] (down to up) <Optional>
			"ath": 0.0,						//Horizontal position in degrees [-180.0, 180.0] (left to right) <Optional>
			"width": 0.05,					//Width (panorama's diameter at percentage) [0.0, 1.0] e.g. 0.05 is the 5% <Optional>
			"height": 0.05,					//Height (panorama's diameter at percentage) [0.0, 1.0] e.g. 0.05 is the 5% <Optional>
			"image": "hotspot.png",			//Image name or URL e.g. hotspot.png or res://raw/hotspot
			"alpha": 0.8,					//Transparency when is not selected the hotspot [0.0, 1.0] <Optional> Note: see notes at the end of literal
			"overAlpha": 1.0,				//Transparency when is selected the hotspot [0.0, 1.0] <Optional> Note: see notes at the end of literal
			"onClick": "lookAt(0.0, 90.0)"	//onClick event <Optional> Note: see the literal 6.3
		}
	]
}

Notes:
* keep options retain the settings loaded before the JSON protocol in progress, allowing you to control the settings for creating virtual tours.
* fov, fovFactor, zoomFactor and zoomLevel should not be used together because they have the same function, where the order of priority is fov, fovFactor, zoomFactor and finally zoomLevel.
* alpha options are float values from 0.0 to 1.0, where 1.0 means without transparency and 0.0 completely transparent.
* All options are case sensitive.

6.3. Hotspot commands
=====================
The commands are case sensitive and they can be used together using the semicolon character. e.g. "onClick": "lookAt(0.0, 90.0, true); load('res://raw/json_spherical2', true, BLEND(3.0, 1.0))".

6.3.1. Load
===========
Description: Load a new panorama
Syntax: load(url, <showProgressBar>, <transition>, <vLookAt>, <hLookAt>)
Parameters:
	url: a string with the JSON file URL to load
	showProgressBar: show progress bar [true, false] <Optional>
	transition: transition function or null <Optional>
		BLEND: Blend transition
		Syntax: BLEND(interval, <zoomFactor>)
		Parameters:
			interval: duration of the transition in seconds [1.0, ...]
			zoomFactor: zoom factor [0.0, 1.0] <Optional>
	vLookAt: camera vertical rotation for the new panorama in degrees [-90.0, 90.0] <Optional>
	hLookAt: camera horizontal rotation for the new panorama in degrees [-180.0, 180.0] <Optional>
Examples:
	load('res://raw/json_cubic', true, BLEND(2.0, 1.0))
	load('res://raw/json_cubic', false, null, 0.0, 90.0)
Note: The commands after load will not be executed.

6.3.2. lookAt
=============
Description: Set the camera rotation
Syntax: lookAt(vLookAt, hLookAt, <animated>)
Parameters:
	vLookAt: vertical rotation in degrees [-90.0, 90.0]
	hLookAt: horizontal rotation in degrees [-180.0, 180.0]
	animated: using animation [true, false] <Optional>
Examples:
	lookAt(0.0, 90.0, true)
	lookAt(0.0, 90.0)

6.3.3. lookAtAndZoom
====================
Description: Set the rotation and zoom of the camera
Syntax: lookAtAndZoom(vLookAt, hLookAt, zoomFactor, <animated>)
Parameters:
	vLookAt: vertical rotation in degrees [-90.0, 90.0]
	hLookAt: horizontal rotation in degrees [-180.0, 180.0]
	zoomFactor: zoom factor [0.0, 1.0]
	animated: using animation [true, false] <Optional>
Examples:
	lookAtAndZoom(0.0, 90.0, 1.0, true)
	lookAtAndZoom(0.0, 90.0, 1.0)

6.3.4. zoom
===========
Description: Set the camera zoom
Syntax: zoom(zoomFactor, <animated>)
Parameters:
	zoomFactor: zoom factor [0.0, 1.0]
	animated: using animation [true, false] <Optional>
Examples:
	zoom(1.0, true)
	zoom(1.0)

6.3.5. fov
==========
Description: Set the field of view of the camera
Syntax: fov(fov, <animated>)
Parameters:
	fov: field of view [0.01, 179.0]
	animated: using animation [true, false] <Optional>
Examples:
	fov(90.0, true)
	fov(90.0)

6.4. See
========
* PLJSONLoader class and PLView load methods.
* The files json_spherical.data, json_spherical2.data, json_cylindrical.data and json_cubic.data are in "res/raw" folder of HelloPanoramaGL example.

7. More information
===================
* For more details check the HelloPanoramaGL example, it is compatible with Android 2.0 or higher.
* To compile the HelloPanoramaGL example, you must import the library as described at the literal 4.1.

8. Supporting this project
==========================
If you want to support this project, please donate to my Paypal account:

https://www.paypal.com/cgi-bin/webscr?cmd=_donations&business=TN942N9FFXYEL&lc=EC&item_name=PanoramaGL&currency_code=USD&bn=PP%2dDonationsBF%3abtn_donateCC_LG%2egif%3aNonHosted
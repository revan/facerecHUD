#facerecHUD

This is the central piece of my WINLAB Summer Internship Project. The other pieces are [HUDOutput](https://github.com/revansopher/HUDOutput) and [facerecserver](https://github.com/revansopher/facerecserver).

The idea of this project is to create a Head-Up Display system that will recognize faces and display relevant information.

## Software Design
### HUDOutput
This is the Android app that runs on the HUD device, in this case a [Recon MOD LIVE HUD](http://www.reconinstruments.com/products/snow-heads-up-display), displays text on the HUD. It's essentially the SDK demo on Bluetooth Chat, with an interface modified for the small screen.
### HUDPassthrough
This is the Android app that runs on a smartphone. This is necessary because the MOD LIVE doesn't have its own camera or networking, so switching to Google Glass would eliminate this need.
### facerecserver
This is the Flask server that runs the Fischerfaces algorithm for facial detection. This is offloaded to server because of potentially expensive computations, but could be incorporated into the main Android app.

## Software Flow
* HUD detects a keypress on Bluetooth remote, forwards it to the phone.
* Phone takes a picture, and if a face is detected, crops to it and sends it to the server.
* Server runs recognition algorithm, sends results to phone.
* Phone looks up result in information database, sends information to HUD.
* HUD displays information. There is much rejoicing.


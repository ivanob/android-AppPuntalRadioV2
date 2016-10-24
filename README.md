# android-AppPuntalRadioV2
Version 2 de la app PuntalRadio. Reescrita con AndroidStudio.
PuntalRadio is the android app from the radio station 'Puntal Radio' (http://www.puntalradio.com/)
It is available for free in Google Play: https://play.google.com/store/apps/details?id=com.ivanob.puntalradio&hl=en

### Hints
- It reproduces by **streaming** the station using the internet connection.
- Connects to **Twitter API** to retrieve the last tweets of the official account.
- Stores externally all the information about the programs and time schedules in JSON files.
- Allows the user to turn off the app in a fixed time (mode sleep).
- Completely redesigned using **Android Studio** and **Material Design** style.
- **+600 installs and 176 active users** (October 2016).
- **19** ratings and score **4.632**.
- Works perfectly either phones or tablets. Widely tested in different configurations.

### Screenshots
![alt tag](https://lh3.googleusercontent.com/Y0ojgOuSOwEiqCJ9T3bKfiH9nq8CqfRadlKIbhP303X5xm5tLQFShDlAlvwd-kcA7kg=h310-rw)
![alt tag](https://lh3.googleusercontent.com/jJvdufIvN_gZjJDw1mb8xc8oSq5PO7lKvHy3Vqolusavy3ZYbRwS2Uq63x5PwDqHbw=h310-rw)
![alt tag](https://lh3.googleusercontent.com/T_9k9kqedspZSG_Nox2cA3JlKM8hanurgSKJ8x152lyAVenAZNl4LaQghD7Yyv-1mQ=h310-rw)
![alt tag](https://lh3.googleusercontent.com/oxZOQpDT12biQTcFk8y09St6Wukje6BV1Gc3XNXHwfZovf5a_9J0N7vNvG_i6QLOk2bz=h310-rw)

### Versions
Current version: 1.3

### Future work
- Evaluate the idea of setting a multimedia footer bar.
- Implement the stop of the stream when there is an incoming call.
- Check the reproduction buffer. While is paused, it is queueing the stream so when you resume the reproduction there is a delay with the current stream. Stop the stream instead of pause?
- Add unit and integration testing for all the project.
- Deep refactor of all the code, specially files relocation. Clean the code and reorganize packages.
- Fix the "Detalles" dropdown in the "Programas" tab. The social buttons are shrink sometimes.
- Remove images and resources not used (xml).
- Update the "Horarios" image.
- Serve the JSON files through a server, and request them dinamically to that service.

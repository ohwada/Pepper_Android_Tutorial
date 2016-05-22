# Pepper Android Tutorial

[Tutorials for Pepper SDK for Android](https://android.aldebaran.com/doc/tutorials.html) <br>
Some samples allowing you to make Pepper speak, step forward, and much more. <br>

There are the source codes in the site of Softbank. <br>
Here are some projects created based on those source codes. <br>

- Say “Hello, world!” <br>
In this example, we use the Interaction service to set up a Say action, in order to make the robot say something. <br>
<img src="https://github.com/ohwada/Pepper_Android_Tutorial/blob/master/PepperTutorialSay/docs/dialog.png" width="300" /> <br>

- Go to one meter forward <br>
In this example, we use the Actuation service to set up a GoTo action, to make the robot move one meter forward. <br>

- Mimic the elephant <br>
In this example, we use the Actuation service to set up an Animate action and we use the mediaPlayer to play a sound that will be triggered on the robot’s speakers. <br>
<img src="https://github.com/ohwada/Pepper_Android_Tutorial/blob/master/PepperTutorialElephant/docs/elephant.png" width="300" /> <br>

- Display humans around distances <br>
In this example, we use the Interaction service to get the list of the persons around the robot. A frame is associated to each person, and the Actuation service also exposes the robot frame. With all that information, we can compute the distance between the robot and each person. <br>

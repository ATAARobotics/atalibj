# atalibj #

ATALibJ (or atalibj) is a library based on [WPILibJ](http://firstforge.wpi.edu/sf/projects/wpilib) for developing robots for the [FIRST Robotics Competition (FRC)](http://www.usfirst.org/). It's two main areas of focus are simplicity and predictability which, together, will help FRC programmers write better code faster by improving ease-of-use.

## Purpose ##

atalibj is ultimately seeking to be a complete replacement for WPILibJ with more uniformity and predictability from the API side. We're looking to provide users with the ability to easily make minor changes between different input and output devices as well as to increase productivity in writing code. Through uniformity, we hope to ensure that the user can spend more time writing code and less time questioning which method to call. We would also like to help make development easier and as such intend on including an improved version of [gordian](https://github.com/Team4334/gordian).

## Using atalibj ##

Using the library is simple, you'll simply need to create a project in your favorite IDE and add the atalibj source to the project's source. For most setups, this means copying our src folder into the src folder in your project. You can find documentation for the project [here](http://summer-of-first.github.io/atalibj/doc/). Good luck and happy coding!

## Contributing ##

Before contributing to atalibj, please make sure that you've read the purpose carefully and understand the purpose of the project! With that in mind, all you need to do is fork the library and commit away. Once you've finished making your changes, go ahead and send us a pull request. Please note that the final decision on pull requests will ultimately be made by [Joel Gallant](https://github.com/joelg236) and that his decision is law.

## Basic Roadmap ##

This is a list of some future changes we'd like to add as of right now:

* Redesign the logging system. -- possibly an SLF4J-type design
* Add a system to make thread behavior more predictable. -- think [Commands](https://github.com/joelg236/robot-code13/blob/master/src/edu/ata/commands/ThreadableCommand.java) or [Events](https://github.com/aaronweiss74/Ultimate-Ascent/blob/new/src/org/usfirst/frc1923/event/Event.java)
* Improved version of [gordian](https://github.com/Team4334/gordian).
* Wrap more sensors and the like within the given interfaces to increase predictability.
* Develop plugins for major IDEs (NetBeans, Eclipse, IntelliJ) to expand IDE and library support.

## Licensing ##

All license information is contained separately in LICENSE.md for easy access.
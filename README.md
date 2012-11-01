###Team 4334 - Alberta Tech Alliance###
###Main Code Repository###
###Git Version Control System###

##Java Code##

This is our team's code repository, used to store our robot code. Branches can be used for other code (Ex. Website). Be sure to understand git fully before making changes.

##Rules##

When working in this repository, be sure to commit to the correct branch. Branches are set up so that sub-teams can work on their own piece of the program, without being bothered by merging and fast-forward updates.

##How we organize the repository##

Branches are subject to the branch tree described in [this playlist](http://www.youtube.com/playlist?list=PLZFDww2a5yeaUMOpYJCpqeZ4K89QTB7s0). If at all you are confused by this, or feel as though you are doing something wrong, contact me (joelgallant236@gmail.com) before messing around. This way that the repo will work is very strict in nature, and if the contracts of branches are broken, merging will become very difficult and cause problems.

###Branches###
There are 9 branches. These branches are organized in a way that different sub-teams can work on code individually pretty well.
It is worth noting that all branches use underscore letters, and they are capatilized just to look nicer.

* *Depreciated*: Used to store history of master. Is usually one of two commits behind master. does not serve any purpose other than letting master get messed up and being able to rebase master to its prefered configuration.
* *Master*: Our basic master branch. Every time we achieve functional robot code in the _testing_ branch, it will be pushed up to master.
* *Testing*: Used to merge all 'sub-branches' of _testing_ into one usable branch to test with. This will be the most commonly used branch to use when testing.
* *Testing.Automation*: Used to merge all 'sub-branches' of _testing.automation_. This branch signifies usage of moving parts of the robot.
* *Testing.Automation.Autonomous*: Used to do autonomous code. All autonomous modes will be stored in this branch, and the commits here will have to do with the _edu.ata.automation.autonomous_ package.
* *Testing.Automation.Dispatch*: Used to store all dispatching code. This basically means anything dealing with threads or game modes. All the commits here will have to do with the _edu.ata.automation.dispatch_ package.
* *Testing.Automation.Driving*: Used to store driving code. This is everything that makes the robot move, interact and do things. All the commits here will have to do with the _edu.ata.automation.driving_ package.
* *Testing.User*: Used to store code that interacts with the user in some way. This is everything from joysticks, to the driverstation to smartdashboard. All commits here will have to do with the _edu.ata.user_.
* *Testing.Vision*: This is used to store all calculations, algorithms and vision tracking. It uses 'vision' as its name to indicate that that will be the most difficult task in the branch. All commits here will have to do with the _edu.ata.vision_ package.


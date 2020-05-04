[![Build Status](https://travis-ci.com/Coms3-Software-Design/Software-Design.svg?branch=master)](https://travis-ci.com/Coms3-Software-Design/Software-Design)
[![codecov](https://codecov.io/gh/Coms3-Software-Design/Software-Design/branch/master/graph/badge.svg)](https://codecov.io/gh/Coms3-Software-Design/Software-Design)






### WE START WITH THE DIAGRAMS THAT DESCRIBE THE SYSTEM WE BUILDING

##### Starting with the Component's Diagrams
* This Digram show how the components of the system a linked to each other
![](Images/component.png)

##### Next in line is the State diagram. 
* Which shows what states we have and how one moves from one state to the other. This is almost similar to the Process flow diagram which follows after this one. Unlike Takealot.com You need to first sign in to the system before you can start viewing the products, and that is the initial state of our system. If you do not have an account then you need to create one. But the state diagram is quite self explanatory.
![](Images/state.png)

##### The Process FLow Activity Diagram
* Here we Describe step by step how does each  end user gets from loging in to successfully buying their product or selling a product. 
* We also go through how each user can and update their profile, change credentials and even upload a profile picture.

![](Images/ProcessFLow.jpeg)

##### The USE CASE Diagram
* Below we show how a person is a user and what priveleges they have within the system.
* Not that administrator means a person selling a product, which gives them the ability to modify changes to their product or even delete their product completely if they choose not to sell it.
* The CLient is a user interested in buying goods from administrators. Each Admin is a client and each Client can be an admin if they choose to sell a product
* Being an Administrator doesn't mean you have to apply to become one. Once you have successfully logged in you can sell or buy goods

![](Images/useCase.png)

##### The Deployment Diagram
* This speaks to how the system works in the top level view. Speaks to how the System is deployed.
* In this instance We deploy it as a mobile app. Which we later might also deploy as a website.
* The end user uses the system as an android device. which communicates to the server through HTTP, Using PHP as a component of the the database management system, the server communicates to the an SQL database.
* Therefore every user needs to be connected to the internet one way or the other to be able to use the application

![](Images/Deployment.jpeg)

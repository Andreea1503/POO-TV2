# POO TV - part 1 #
```Copyright Spinochi Andreea, 322CA, 2022```

### packages: ###

``` database ```

#### classes: ####

* Database
    * contains the workflow of the application, and it is an implementation
      of a singleton database that keeps track of the current page and makes the
      excution decision depending on the type of action initiated

* Page
    * contains the information about the current page, and it is the place where
      the actions are executed using a factory design pattern for every type of
      action

```database_acion``` - package included in ```database```

#### classes: ####

* Action
    * contains the information about the action that is executed, and it is the
      place where the action is executed using a factory design pattern for every
      type of action

* DatabaseAction
    * Singleton class that encapsulates database actions.

* DatabaseActionFactory
    * Factory class that creates the appropriate action based on the type of
      action

* DeleteAction
    * Class that implements the delete action

*AddAction
    * Class that implements the add action

```return_properies_strategy``` - package included in ```database_action```

#### classes: ####

* PremiumReturnPropertiesStrategy
    * Concrete implementation of the ReturnPropertiesStrategy interface for returning properties
      for a premium user.

* StandardReturnPropertiesStrategy
    * Concrete implementation of the ReturnPropertiesStrategy interface for returning properties
      for a standard user.

* ReturnPropertiesStrategy
    * Interface for returning properties for a user.

``` change_page``` - package included in ``` database ```
#### classes: ####

* ChangePageActionFactory
    * creates a factory that makes the decision of which action to execute
      depending on the type of the issued action

* ChangePageAction
    * it is an interface that is implemented by all the actions that can be
      executed

* ChangePageActionTo*
    * classes that implement the ChangePageAction interface and execute the
      action to change the page to the one specified in the name of the class

``` on_page ``` - package included in ``` database ```

#### classes: ####

* OnPageActionFactory
    * creates a factory that makes the decision of which action to execute
      depending on the type of the issued action

* OnPageAction
    * it is an interface that is implemented by all the actions that can be
      executed

* OnPageAction*
    * classes that implement the OnPageAction interface and execute the
      action to change something on the current page

```purchasing_strategy``` - package included in ```on_page```

#### classes: ####
* PremiumPurchasingStrategy
    * implements the PurchasingStrategy interface and it is used to purchase
      premium content

* StandardPurchasingStrategy
    * implements the PurchasingStrategy interface and it is used to purchase
      standard content

* PurchasingStrategy
    * it is an interface that is implemented by all the purchasing strategies

```subscription``` - package included in ```on_page```

#### classes: ####
* MovieGenre
    * Class that represents a movie genre and implements the Subject interface

* Notification
    * Class that represents a notification that is sent to an observer

* Observer
    * The Observer interface defines a method for updating an observer with a new notification.

* Subject
    * The Subject interface defines the methods that a class implementing this interface should
    * have.

``` input ```

#### classes: ####

* Input
    * reads the input from the json file

```action``` - package included in ```input```

#### classes: ####

* ActionsInput
    * holds the information about the actions that are read from the json file

```user``` - package included in ```input```

#### classes: ####

* UsersInput
    * holds the information about the users that are read from the json file

```movie``` - package included in ```input```

#### classes: ####
* MoviesInput
    * holds the information about the movies that are read from the json file

_The rest of the classes are used to hold details about the users, movies and
actions that are read from the json file._

``` write ```

#### classes: ####

* Write
    * writes the output to the json file

``` Main ```

* contains the main method that creates the database and starts the application


### Design Flow ###

The application starts out in Main. From there, the database is instantiated
and the input is read from the json file. The result of this will be an Input
object that contains the information about the users, movies and actions that
will be given to the database to be processed by calling the method
``` databaseNavigation()```. This method will create a Page object that will be
used to execute the actions.

Firstly, the current page name will be set to "Homepage neautentificat" and the
actions will be parsed. Then, depending on the type of action, the method
``` onPageAction() ``` or ``` changePageAction() ``` will be called. These
methods have a factory design implemented, so that the decision of which action
to execute is made by the factory. The factory will create an object that will
implement the OnPageAction or ChangePageAction interface, and the method
``` execute() ``` will be called on that object. Depending on the type of
method called, the action will be executed on the current page or the page will
be changed. Each method that is executed has a different implementation, and it
is described in the class that implements it.

The output is implemented in the ``` write ``` package. It uses only one method
called ``` writePageError() ``` that writes the output to the json file by
adding every field of the class that is passed as a parameter.

### Design Patterns ###

_Used design patterns:_

* Singleton
    *  the singleton pattern has been used in creating the database since it
       has to be a globally accessible, persistent instance. The variation of
       Singleton used is the one outlined by Bill Pugh since it is an easy to
       implement, efficient and thread-safe variation of the Singleton Pattern.

* Factory
    * this pattern was used in creating the ``` on_page ``` , ``` change_page```  and ```database_action```
      factories since it allows for the easy execution of the
      commands based on a generic interface.

* Observer
    * this pattern was used in facilitating the other interaction between the users and the movies.
  In this implementation, the users are the Observers and the movies are the Observables.
  The users will observe modifications that happen to the movies and will get a notification if a 
  movie is from the list of subscribed genres of the user. One user will observe multiple movies, while one
  movie will be observed by multiple users.

* Strategy
    * this pattern was used in implementing the purchasing strategies for the users. The users will
    have a different purchasing strategy depending on their subscription type. The strategy pattern
    allows for the easy implementation of the different purchasing strategies. The strategy pattern
    was also used in implementing the return properties strategy for the users. The users will have
    a different return properties strategy depending on their subscription type.
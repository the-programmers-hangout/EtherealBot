# Contribution Guidelines
Below, you can find all of the information pertaining to the design, continued 
development and general maintenance of this bot.

## General structure
KUtils provides some relatively useful things for working on this bot, which 
will allow you to navigate the project with *some* sense of direction. 

Firstly, command-set definitions *must* go in the 
`me.aberrantfox.etherealbot.commands` package. If they are not put there,
they will not be picked up. 

Secondly, listener definitions *must* go in the 
`me.aberrantfox.etherealbot.listeners` package. If they are not put there,
they will also be ignored.

With this information in mind, you know roughly where to go, and where to put
things depending on what you're looking for. There are still some other packages
that are described here:

 - **Services** - This package contains any supporting logic objects. So for 
                  example, if the goal was to make a command for statistics,
                  you might put a `StatisticsCollector` object in here. 
 - **Configuration** - This package is concerned with... you guessed it,
                       configuration. There shouldn't be all that much
                       findable in here, you can see how the config is
                       picked up, any additional message/language files
                       will also go in here.
 - **Persistence** - The persistence package is for any handles related
                     to the database. Any database operation should go
                     in here so it is clearly outlined and findable.
                     
                     
## Using Maven
Maven is the dependency and build manager for this bot. It has two primary jobs:
 - Create reproducible builds
 - Pull in any dependencies the bot may need
 
There are many things that maven can do outside of this, but these two are the
main uses here. There are a few commands that you will need to know:

1. `mvn clean package` - This is pretty simply the build command. You can
                         find the output in target/. The jar file you want
                         is the one marked as `jar-with-dependencies`. 
2. `mvn detekt:check`  - This will generate a report in detekt/ - if you are in
                         intellij, simply open the directory, then open
                         `detekt-report.html`, in the top right there should
                         be a little hover menu, to allow you to open it in your
                         browser of choice automatically. Anytime you re-run 
                         the command, this will update that file.

## Branch scheme
 - `master` - Only updated alongside release branches 
 - `develop` - What to make pull requests against
 - `feature/xyz` - The feature branch for `xyz` feature. 


## Making a pull request
If you want your pull request to go through, all you should:
 - Have a description of the changes you made
 - Make changes atomically - Do not contribute big massive features in one
   single PR - that is not easily digestible for a reviewer. 
 - Have good formatting
 - Test thoroughly


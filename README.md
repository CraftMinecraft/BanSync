# About BanSync
After you ban a user, what then?  Now you have to go around and remove the locks the user has made, their stores, their economy, etc.  That is where BanSync comes into play, When a user is kicked from the server, it will check to see if they are banned, and then if they are, it will automatically take care of the rest for you.

## Supported Ban Plugins
* Vanilla Ban System
* Essentials
* Any other plugin that supports the Vanilla Ban System
* Let me know if this works with any other plugins

## Supported Plugins
### LWC
* Removes Locks that the player made
### PlotMe
* Removes Plots that the player made
### Vault
* Drains the players bank account to $0.00
* Deletes the account if supported by your economy

## Commands
### /bansync removeuser {username}
Manually remove the user

## Permissions
### bansync.removeuser
Allows the user to run /bansync removeuser, Defaults to op

## Future Plans
* Add configuration file with configuration options
* Add support for other Plugins
	* Lockette
	* Towny
	* Chest Shop
	* Citizens / Citizens 2 / NPCs
	* CitiTrader
	* World Guard (Regions)
	* Essentials (Delete users settings, items in hand, homes, etc)
	* Please suggest more plugins that you would like support added too...
* Timeout before sync happens

## Source Code
[https://github.com/CraftMinecraft/BanSync](https://github.com/CraftMinecraft/BanSync)

## Change Log
### Version 0.07
* TODO

### Version 0.05
* Moved project to Beta status
* Added command /bansync help

### Version 0.04
* Added command /bansync removeuser

### Version 0.03
* Added support for Vault for Economy

### Version 0.02
* Added support for PlotMe

### Version 0.01
* First stable Alpha Release of the plugin
* Supports LWC and the Vanilla Ban System
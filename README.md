# About BanSync
After you ban a user, what then?  Now you have to go around and remove the locks the user has made, their stores, their economy, etc.  That is where BanSync comes into play, When a user is kicked from the server, it will check to see if they are banned, and then if they are, it will automatically take care of the rest for you.

## Latest Version
Latest Recommended Build: **1.0-b16**

## Supported Ban Plugins
* Vanilla Ban System
* Essentials
* Any other plugin that supports the Vanilla Ban System
* Let me know if this works with any other plugins

## Supported Plugins
* LWC
	* Removes Locks that the player made
* PlotMe
	* Removes Plots that the player made
	* Removes player from plots where user has been added too
* Vault
	* Drains the players bank account to $0.00
	* Deletes the account if supported by your economy
* Grief Prevention
	* Removes protections that the player made
* WorldGuard
	* Removes regions that are owned by the player

## Configuration
Each plugin hook can be turned on / off in the configuration as well as the automatic removal on ban.

Default Configration:
```yaml
AutoDeletePlayerOnBan: true

EnableLWC: true
EnablePlotMe: true
EnableVault: true
EnableGriefPrevention: true
```

## Commands
* /bansync removeuser {username}
	* Manually remove the user

## Permissions
* bansync.removeuser
	* Allows the user to run /bansync removeuser, Defaults to op

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
Latest source code can be found on our GitHub page at: [https://github.com/CraftMinecraft/BanSync](https://github.com/CraftMinecraft/BanSync)

## Development Versions
Latest unpublished plugins can be found on our development website at: [http://dev.craftminecraft.net/plugins/bt2](http://dev.craftminecraft.net/plugins/bt2)

## License Information
Copyright (C) 2013 CraftMinecraft

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
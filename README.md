# CustomClientBlocker

A plugin that blocks connecting a set of specified clients

## Installation

asdfasdf

## Usage

### Commands

The base command is `/clientblocker` or `/ccb`

And base permission is `customclientblocker.command`

| Command | Description                                      | Permission                                                                    |
|---------|--------------------------------------------------|-------------------------------------------------------------------------------|
| players | Lists players, but the connected client is shown | `customclientblocker.command.players`                                         |
| list    | Lists which clients are blocked                  | `customclientblocker.command.list`                                            |
| add     | Adds an entry to client blacklist                | `customclientblocker.command.add` or `customclientblocker.command.modify`     |
| remove  | Removes an entry to client blacklist             | `customclientblocker.command.remove`  or `customclientblocker.command.modify` |
| reload  | Reloads the setting from config file             | `customclientblocker.command.reload`                                          |

### Bypassing specific players

You can add an except by adding permission `customclientblocker.bypass` to a player

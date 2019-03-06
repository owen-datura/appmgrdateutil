# Application Manager Date Utility

One aspect of my job involves encountering dates conforming to "Application Manager" format. While I don't have first-hand experience with the system in question, it's my understanding that it was a mainframe-era record handling and reporting system. With that background out of the way, you should know that it's pretty common to encounter dates defined in "AM format" during my daily tasks. Not unlike the Unix epoch, this format plants a flag in a particular start date (01/01/1900) and derives a value based on days since that date. In order to more rapidly convert to/from this value, I'd created this basic utility.

## TODO
* On open the app should default to showing the current date and its AM conversion.
* Give the app a distinctive icon
* Make one of the buttons default (`frame.getRootPane().setDefaultButton(button)`)
* Add command-line conversion (so you can supply a value when calling and instead of presenting it in the UI it just prints to stdout)
* Change positioning behavior so the window doesn't appear in the top-left (call `setLocationRelativeTo(null)` but **after** pack())

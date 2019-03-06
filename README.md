# Application Manager Date Utility

One aspect of my job involves encountering dates conforming to "Application Manager" format. It's my understanding that this format works similarly to the Unix epoch (albeit only in whole dates, not minutes, seconds, etc.) and represents the number of days since 01/01/1900.

## TODO
* On open the app should default to showing the current date and its AM conversion.
* Give the app a distinctive icon
* Make one of the buttons default (`frame.getRootPane().setDefaultButton(button)`)
* Add command-line conversion (so you can supply a value when calling and instead of presenting it in the UI it just prints to stdout)
* Change positioning behavior so the window doesn't appear in the top-left (call `setLocationRelativeTo(null)` but **after** pack())

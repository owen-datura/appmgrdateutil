# Application Manager Date Utility

One aspect of my job involves encountering dates conforming to "Application Manager" format. It's my understanding that this format works similarly to the Unix epoch (albeit only in whole dates, not minutes, seconds, etc.) and represents the number of days since 01/01/1900.

## TODO
* Give the app a distinctive icon
* Make one of the buttons default?
* Add command-line conversion (so you can supply a value when calling and instead of presenting it in the UI it just prints to stdout)
* Change positioning behavior so the window doesn't appear in the top-left (call `setLocationRelativeTo(null)` but **after** pack())

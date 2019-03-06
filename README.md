# Application Manager Date Utility

One aspect of my job involves encountering dates conforming to "Application Manager" format. 

While I (mercifully?) don't have first-hand experience with the system in question, it's my understanding that it was a popular mainframe-based record handling and reporting framework. With that background out of the way, you should know that one peculiarity of this system is its date handling: dates are provided in "AM format" and require conversion to be better understood by mere mortals. Not unlike the Unix epoch, this format works by declaring a particular date as the beginning of history (01/01/1900) and derives values based on the # of days elapsed since. 

To aid in this pursuit, this simple utility was created.

## TODO
* On open the app should default to showing the current date and its AM conversion.
* Give the app a distinctive icon
* Make one of the buttons default (`frame.getRootPane().setDefaultButton(button)`)
* Add command-line conversion (so you can supply a value when calling and instead of presenting it in the UI it just prints to stdout)
* Change positioning behavior so the window doesn't appear in the top-left (call `setLocationRelativeTo(null)` but **after** pack())

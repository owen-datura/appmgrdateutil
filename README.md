# Application Manager Date Utility

One aspect of my job involves encountering dates conforming to **Application Manager**[^1] format. 

While I (mercifully?) don't have first-hand experience with the system in question, it's my understanding that it was a popular mainframe-based record handling and reporting framework. With that background out of the way, you should know that one peculiarity of this system is its date handling: dates are provided in "AM format" and require conversion to be better understood by mere mortals. Not unlike the Unix epoch, this format works by declaring a particular date as the beginning of history (01/01/1900) and derives values based on the # of days elapsed since. 

To aid in this pursuit, this simple utility was created.

[^1]: Unfortunately the extremely generic name makes it difficult for me to get a historical perspective about it. I *have* found some modern references to a "Tivoli Composite Application Manager" but have no idea if that's a contemporary version of the same software or just happens to have an overlapping name. 🤷

## TODO
* On open the app should default to showing the current date and its AM conversion.
* [Lilian date](https://en.wikipedia.org/wiki/Lilian_date) (days since 10/15/1582)
* Give the app a distinctive icon (apparently you can call window.setIconImages?)
  * Let's use something from the 3,000 icons set
* Make one of the buttons default (`frame.getRootPane().setDefaultButton(button)`)
* Add command-line conversion (so you can supply a value when calling and instead of presenting it in the UI it just prints to stdout)
* Change positioning behavior so the window doesn't appear in the top-left (call `setLocationRelativeTo(null)` but **after** pack())
* Nice-to-have: 
  * Formatting of the Gregorian date should be better, maybe showing delimiters when populating and removing them on entry? 
  * Include the formatted month in text as well as numerically (avoids errors when skimming the output and miscounting the month)
  * There should be feedback for the cases where the conversion fails due to a missing value or inappropriate data. 
  * Can some validation be added to the fields themselves (i.e. forcing numeric only)?

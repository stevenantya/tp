# **User Guide for SecureNUS**

## **<span style="text-decoration:underline;">Introduction</span>**
SecureNUS is a desktop Command Line Interface (CLI) application that allows users to manage and store their passwords 
securely.

SecureNUS operates on a command-line interface that allows for a simple and efficient management of passwords for fast 
typists. 

- [Quick Start](#span-styletext-decorationunderlinequick-startspan)
- [Feature List](#span-styletext-decorationunderlinefeature-listspan)
- [Feature Details](#span-styletext-decorationunderlinefeature-detailsspan)
- [FAQ's](#span-styletext-decorationunderlinefaqsspan)
- [Command Summary](#span-styletext-decorationunderlinecommand-summaryspan)
## **<span style="text-decoration:underline;">Quick Start</span>**
1. Ensure you have Java `11` or above installed on your computer.
2. Download the latest `tp.jar` from [here](https://github.com/AY2223S2-CS2113-T15-2/tp/releases).
3. Copy the `tp.jar` file to the folder for SecureNUS.
4. Open a command terminal, `cd` into the folder you put your `tp.jar` file in, and use the `java -jar tp.jar` command
to run the application.
5. Type the command in the command box and press Enter to execute it.<br> e.g. typing `menu` and pressing Enter key 
will open the menu window.
 
## **<span style="text-decoration:underline;">Feature List</span>**
1. [Add `new` password](#add-a-new-password--new)
   - Type of Passwords
     1. Basic Password
     2. Credit Card
     3. Crypto Wallet
     4. NUSNet
     5. StudentID - Starting with A
     6. Wifi Password
2. [`delete` a password](#deleting-a-password--delete) 
3. [`list` all passwords](#general-format--list-ffoldername)
4. [`view`password](#viewing-password-in-command-line--view) 
5. [`search` for password](#general-format--search-nnamelike--f-ffolderlike)
6. [`save` the passwords into a .txt file](#general-format--save) 
7. [`load` all passwords from a .txt file](#general-format--load--a-append-ffilepath)
8. [`edit` 
the old passwords and
update them](#general-format--edit-ppasswordname--f-nfnewfoldername--d-ndnew description--n-npnewpasswordname)
9. [`menu` of all the commands for a quick reference](#menu--menu)
10. [`exit` for users to close SecureNUS](#exiting-the-program--exit)

## **<span style="text-decoration:underline;">Feature Details</span>**

#### Note about the command format:
* Words in `UPPER_CASE` are the parameters to be supplied by the user.
* Items in square brackets `[]` are optional.
* Items with `[…]` after them can be used multiple times including zero times.
* Extraneous parameters for commands that do not take in parameters (such as `menu`, and `exit`) will be ignored. <br>
e.g. if the command specifies `menu 234`, it will be interpreted as `menu`.

<br>

### [Add a new password: `new`](#span-styletext-decorationunderlinefeature-listspan)
- Adds a new password in SecureNUS based on the type of the password.
- Opens a list of questions to fill in including username, password, etc.
- Ctrl-C at any time to cancel the creation.

###### General Format:  `new [o/ OPTIONS] PASSWORD_NAME [f/ FOLDER_NAME]`
###### Types of passwords: 
1. Basic Password `new PASSWORD_NAME`
2. Credit Card `new o/CreditCard PASSWORD_NAME`
3. Cryptocurrency Wallet `new o/CryptoWallet PASSWORD_NAME`
4. NUSNet ID `new o/NUSNet PASSWORD_NAME`
5. Student ID `new o/StudentID PASSWORD_NAME`
6. Wifi Password `new o/WifiPassword PASSWORD_NAME`

###### Examples: <br>
```
new password_name 
new password_name /f folder_name 
new o/CreditCard password123
new o/CryptoWallet password234 f/ folder_name
new o/NUSNet My nus net
new o/StudentID A02888888
new o/WifiPassword Home wifi
```
<br>

### [Deleting a password: `delete`](#span-styletext-decorationunderlinefeature-listspan)
- Deletes a list of passwords. The hash or name of the password can be used.
###### General Format: `delete p1/PASSWORD_1 [p2/PASSWORD_2] […]`
###### Examples: <br>
```
delete p1/canvas.nus.edu.sg p2/www.gmail.com
```
<br>

### [List all passwords: `list`](#span-styletext-decorationunderlinefeature-listspan)
- List all the passwords in the folder specified.
- If a folder is added, it will only list out passwords in the folder else it will list out all the passwords stored by
  the user irrespective of folder name.
###### General Format: `list [f/FOLDER_NAME]`

###### Examples: <br>
**<span style="text-decoration:underline;"></span>**
```
list
list f/School_Websites
```
Output: 
```
ID: 1 | canvas.nus.edu.sg	| School Module Website	
ID: 2 | nus.edu.sg		| School Website		
ID: 3 | outlook.com		| School Email		
```

<br>

### [Viewing Password in Command Line: `view`](#span-styletext-decorationunderlinefeature-listspan)

- Since the password is sensitive, it will only be revealed on specific command. Either the password_name or 
password_hash must be used to reveal it. 
- For passwords that are “password-required”, a secret password is required to reveal.

###### General Format: `view p/PASSWORD_NAME`

###### Examples: <br>

```
View p/canvas.nus.edu.sg
```
Output:
```
Enter secret password to reveal "canvas.nus.edu.sg":				
—-----Password:securePassword@NUS123—-----------------------------------
```

<br>

### [Search Passwords: `search`](#span-styletext-decorationunderlinefeature-listspan)
- Returns all passwords that have a similar name to “NAME_LIKE” or folder with name like “FOLDER_LIKE”

###### General Format: `search n/NAME_LIKE [-f f/FOLDER_LIKE]`

###### Examples: <br>

```
search n/canvas
search n/canvas -f f/School
```

Output:
```
FOUND 1 Match!
ID: 1 | canvas.nus.edu.sg	| School Module Website	
```
<br>


### [Saving Passwords: `save`](#span-styletext-decorationunderlinefeature-listspan)
- Saves all the passwords in an output logfile: `./logs/out`
###### General Format: `save`

<br>

### [Loading Passwords: `load`](#span-styletext-decorationunderlinefeature-listspan)
- Loads all password from a textfile (.txt)
- Use -a if you want these to be appended. Otherwise, it will erase and overwrite all other passwords. 
- Default input logfile: `./logs/in`
###### General Format: `load [-a, –append] [f/FILE_PATH]`
###### Examples: <br>
```
load -a f/~/users/cs2113/passwords.txt
```
<br>

### [Editing Password: `edit`](#span-styletext-decorationunderlinefeature-listspan)
- Allows users to edit exisitng password.
- If no flags added, it will open a prompt to input your new password.

###### General Format: `edit p/PASSWORD_NAME [-f nf/NEW_FOLDER_NAME] [-d nd/NEW_DESCRIPTION] [-N np/NEW_PASSWORD_NAME]`

###### Examples: <br>

```
edit p/canva.com -f nf/School_Stuffs -d nd/My Modules Website -N np/canvas.nus.edu.sg
```
Output:
```
Enter your new password:							
```
<br>

### [Menu for reference: `menu`](#span-styletext-decorationunderlinefeature-listspan)
- Allows users to refer to all the CLI commands by typing one command.

###### General Format: `menu`

<br>

### [Exiting the Program: `exit`](#span-styletext-decorationunderlinefeature-listspan)
- Exits the program. 
- Shows a message with all possible commands and syntax.

###### General Format: `exit`

<br>

## **<span style="text-decoration:underline;">FAQ's</span>**

**Q**: I forgot to save before exiting. Are all my passwords lost?

**A**: By default, the program will save the last copy of the passwords before exiting in the filepath ./logs/last_exit if allowed to exit gracefully

**Q**: What if I create a password without a folder?

**A**: Do not worry! Your password is still saved in the unnamed folder.

## **<span style="text-decoration:underline;">Command Summary</span>**


<table>
  <tr>
   <td>Action
   </td>
   <td>Format/Examples
   </td>
  </tr>
  <tr>
   <td>New
   </td>
   <td><code>new [o/OPTIONS] PASSWORD_NAME </code>
   </td>
  </tr>
  <tr>
   <td>Delete
   </td>
   <td><code>delete p1/PASSWORD_1 [p2/PASSWORD_2] [...]</code>
   </td>
  </tr>
  <tr>
   <td>List
   </td>
   <td><code>list [f/FOLDER_NAME]</code>
   </td>
  </tr>
  <tr>
   <td>View
   </td>
   <td><code>view p/PASSWORD_NAME</code>
   </td>
  </tr>
  <tr>
   <td>Search
   </td>
   <td><code>search n/NAME_LIKE [-f f/FOLDER_LIKE]</code>
   </td>
  </tr>
  <tr>
   <td>Save
   </td>
   <td><code>save</code>
   </td>
  </tr>
 <tr>
   <td>Load
   </td> 
   <td> <code> load [-a, –append] [f/FILE_PATH]</code>
   </td>
 </tr>
  <tr>
   <td>Edit
   </td>
   <td><code>edit p/PASSWORD_NAME [-f nf/NEW_FOLDER_NAME] [-d nd/NEW_DESCRIPTION] [-N np/NEW_PASSWORD_NAME]</code>
   </td>
  </tr>
  <tr>
   <td>Help
   </td>
   <td><code>help</code>
   </td>
  </tr>
</table>


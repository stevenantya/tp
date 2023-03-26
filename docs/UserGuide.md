# **SecureNUS User Guide**


## **<span style="text-decoration:underline;">Feature List</span>**



1. Add new password `new`
    1. Hide password while typing it
2. Delete password `delete`
3. List all password `list`
4. Reveal password `view`
5. Search for password `search`
6. Save the passwords into a text file `save`
7. Load passwords from a text file `load`
8. Edit password `edit`


## **<span style="text-decoration:underline;">User Guide Draft</span>**

SecureNUS is a desktop CLI app for managing and storing passwords. SecureNUS provides the functionalities of a regular password manager tool, but on CLI. Simple and efficient to use especially for fast typists.


### Features


#### Additional Notes



* Words in `UPPER_CASE` are the parameters to be supplied by the user.
* Items in square brackets `[]` are optional.
* Items with `[…]` after them can be used multiple times including zero times.
* Extraneous parameters for commands that do not take in parameters (such as help, list, exit and clear) will be ignored.


#### Viewing help: `help`

Shows a message with all possible commands and syntax.


#### Adding a password: `new [o/OPTIONS] p/PASSWORD_NAME `

Opens a list of questions to fill in including username, password, etc.

Ctrl-C at any time to cancel the creation.

For now, there are no options.

Examples: \
`new p/canvas.nus.edu.sg`


#### Deleting a password: `delete p1/PASSWORD_1 [p2/PASSWORD_2] […]`

Deletes a list of passwords. The hash or name of the password can be used.

Examples:


```
delete p1/canvas.nus.edu.sg p2/www.gmail.com
```



#### List all passwords: `list [f/FOLDER_NAME]`

List all passwords in the format: `PASSWORD_ID_HASH | PASSWORD_NAME | PASSWORD_DESCRIPTOR `

If a folder is added, it will only list out passwords in the folder \
Examples:


```
list f/School_Websites
```


Output:


```
ID: 1 | canvas.nus.edu.sg	| School Module Website	
ID: 2 | nus.edu.sg		| School Website		
ID: 3 | outlook.com		| School Email		
```



#### Revealing Password in Terminal: `view p/PASSWORD_NAME`

Since the password is sensitive, it will only be revealed on specific command. Either the password_name or password_hash must be used to reveal it.

For passwords that are “password-required”, a secret password is required to reveal.

Examples:


```
View p/canvas.nus.edu.sg
```


Output:


```
Enter secret password to reveal "canvas.nus.edu.sg":				
—-----Password:securePassword@NUS123—-----------------------------------
```



#### Search Passwords: `search n/NAME_LIKE [-f f/FOLDER_LIKE]`

Returns all passwords that have a name like “NAME_LIKE” or folder with name like “FOLDER_LIKE”

List all passwords/ folders in the format: `PASSWORD_ID_HASH | PASSWORD_NAME | PASSWORD_DESCRIPTOR `

Examples:


```
search n/canvas
search n/canvas -f f/School
```


Output:


```
FOUND 1 Match!
ID: 1 | canvas.nus.edu.sg	| School Module Website	
```



#### Saving Passwords: `save`

Logs all password into an output logfile: ./logs/out


#### Loading Passwords: `load [-a, –append] [f/FILE_PATH]`

Loads all password from a textfile

Use -a if you want these to be appended. Otherwise, it will erase and overwrite all other passwords.

Default input logfile: ./logs/in

Examples:


```
load -a f/~/users/cs2113/passwords.txt
```



#### Editing Password: `edit p/PASSWORD_NAME [-f nf/NEW_FOLDER_NAME] [-d nd/NEW_DESCRIPTION] [-N np/NEW_PASSWORD_NAME] `

If no flags added, it will open a prompt to input your new password.

Examples:


```
edit p/canva.com -f nf/School_Stuffs -d nd/My Modules Website -N np/canvas.nus.edu.sg
```


Output:


```
Enter your new password:							
```



#### Exiting the Program: `exit `

Exits the program.


### FAQ

**Q**: I forgot to save before exiting. Are all my passwords lost?

**A**: By default, the program will save the last copy of the passwords before exiting in the filepath ./logs/last_exit if allowed to exit gracefully


### Command Summary


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


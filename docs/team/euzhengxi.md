# Zheng Xi's - Project Portfolio Page

## Overview
>SecureNUS is a Command-Line-Interface (CLI) application that allows users to store sensitive credentials locally.
Examples of such credentials include basic password, credit card details as well as CryptoWallet fields.
Information submitted by users are encrypted to safeguard the interests of the user in the event the device is lost
or hacked.

<br>

## Summary of Contributions
### [X] Code contributed
[RepoSense link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=euzhengxi&breakdown=true)
<br>
***
### [X] Features <br>
#### 1. Save Command - `$save` <br>
All credentials submitted by users thus far will be written to the database. This function is automatically triggered 
when new secrets added, edited or deleted from the database.
```
Enter Command: $save
_____________________________________________________
Saving secrets to storage, please do not close application...
_____________________________________________________
_____________________________________________________
Save complete. All secrets saved into assets/database.txt.
If exporting to another device, save this file into the root directory's assets/database.txt before you start me again!
_____________________________________________________
```
#### 2. Backend - Saving and Loading of user data <br>

+ ##### Basic reading and writing Capabilties <br> 
  File paths are retrieved via `$System.getProperty(Backend.USER_DIRECTORY_IDENTIFIER)`
  so as to circumvent differences in path syntax across different operating systems. A folder and a database.txt file is
  generated on behalf of users.  
  1. Special characters `$~!@#$%^&*()-=_+[]\{}|;':",./<>?`can be submitted in certain fields. To ensure the veracity of 
  the data, modifications have to be performed before they are written into the database. When reading from the database, 
  these modifications have to be reversed accordingly.<br> <br>
+ #### Encryption before data is written to the database.
  Sensitive fields like `$username` and `$password` are encrypted into the database when it is written to the database and
  decrypted again when loaded into the program <br> <br>
+ #### Ensuring data validity<br> 
  Intentional / unintentional edits may be performed on the data in database.txt file. Therefore checks have to be
  performed to verify the veracity of the data when a new session starts. These checks can be divided into 4 main categories: <br> <br>
   1. Ensuring `$name` and  `$folder name` only contain alphanumeric characters
  ```java
     if (Secret.isIllegalName(input[2]) || !SecretMaster.isLegalFolderName(input[3]))
  ```
   2. Ensuring the other fields in a particular secret are valid with respect to the context they are used.
  ```java
    if (!creditCardNumber.matches(CREDIT_CARD_NUMBER_FMT)) {
        throw new InvalidCreditCardNumberException();
    }
    if (!expiryDate.matches(EXPIRY_DATE_FMT)) {
        throw new InvalidExpiryDateException();
    }
    if (!isLegalCvcNumber(cvcNumber)) {
        throw new InvalidCVCNumberException();
    }
  ``` 
   3. Ensuring that there are no secrets with duplicate names
  ```java
      if (secretNames.contains(secret.getUid())) {
          throw new RepeatedIdException();
      }
  ```
   4. Final checks using the hashcode of the data <br>
      A hashcode is generated for all information written to the database. Before data is loaded into the program,
      this hashcode will be cross referenced. This serves as the final line of defense against any manipulation of user
      user data in the database
  ```java
     public static String hash(String data) {
        int hashcode = 0;
        ...
     }
  ```
<br>

#### 3. Logging Capabilities - Logging of appropriate user data to assist in debugging 
- Logging Architecture <br>
  All user input is logged
```java

---------------------------------| Session began at: 2023-04-09T23:28:12.788915800Z |-------------------------------------------------------------------| Session began at: 2023-04-09T23:29:18.053986200Z |-----------------------------------

        command by user:  new pass10
        fields:  idk.com,  c/,
        >>>>user cancelled operation

        command by user:  exit
        fields:
        ------------
        | ALERT !!!|
        ------------
        Program Crashed:  unexpected exception: null
        Time: 2023-04-09T23:47:28.668632600Z

```
        ...
  ```
Within the abstract method format, all relevant information are rearranged and formatted using
static methods based on the context of the information.
```java
    if (logArray[0].equals("start")) {
        return SecureNUSLogFormatter.startOfSessionLog(record);
    }
```
<br>

***
### [X] Assertions <br>
Added assertions to the code to better safeguard the program against unexpected inputs and lapses in programming logic
``` java
    assert this.storage != null;
    assert index >= 0;
    if (index >= storage.size()) {
        throw new ArrayIndexOutOfBoundsException();
    } 
```

### [X] Contributions to Team Based Tasks <br>
1. Wrote the junit tests for various Backend Functionalities
2. Authored the text-ui-tests to automate testing for the core functionalities implemented in the program
3. Team PR reviewed [[#48](https://github.com/AY2223S2-CS2113-T15-2/tp/pull/48)]

### [X] Community

- Contributed to forum discussions [[1](https://github.com/nus-cs2113-AY2223S2/forum/issues/45)]
- Reported bugs and suggestions for other teams in the class [[1](https://github.com/nus-cs2113-AY2223S2/ip/pull/20/files/567e6fcf4d4be8fb7d3f6b8b456ad72597cf7388),
  [2](https://github.com/nus-cs2113-AY2223S2/ip/pull/114/files/2fa55392cbbbf776812bc0878ca9b4040edc5267),
  [3](https://github.com/euzhengxi/ped)]

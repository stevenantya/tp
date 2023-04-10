[//]: # (@@author DeepanjaliDhawan)
# SecureNUS
SecureNUS is a password manager application that allows users to store and manage their secrets and 
other sensitive information securely.

## Features
+ Add, delete, and edit secrets
+ Organize secrets into folders
+ Search for secrets by name or folder
+ Generate strong passwords
+ View password strength and last modified date for each secret
+ Automatic saving and loading of data

## Useful links:
* [User Guide](UserGuide.md)
* [Developer Guide](DeveloperGuide.md)
* [About Us](AboutUs.md)

## Getting Started
### Prerequisites
+ Java Runtime Environment (JRE) version 11 installed on your machine
+ Access to the command line interface

### Installation
+ Download the SecureNUS JAR file from the latest release on the repository.
+ Open a terminal or command prompt.
+ Navigate to the directory where the JAR file is located.
+ Run the following command: `java -jar [CS2113-T15-2][SecureNUS].jar`

## Usage
<table>
    <tr>
        <td>Action </td>
        <td>Format/Examples </td>
    </tr>
    <tr>
        <td>New </td>
        <td><code>new [o/OPTION] SECRET_NAME [f/FOLDER_NAME] </code> </td>
    </tr>
    <tr>
        <td>Delete</td>
        <td><code>delete SECRET_NAME1 [SECRET_NAME2] [...] </code></td>
    </tr>
    <tr>
        <td>List</td>
        <td><code>list [f/FOLDER_NAME]</code></td>
    </tr>
    <tr>
        <td>View </td>
        <td><code>view SECRET_NAME</code></td>
    </tr>
    <tr>
        <td>Search</td>
        <td><code>search SECRET_NAME_LIKE [f/FOLDER_NAME]</code></td>
    </tr>
    <tr>
        <td>Save</td>
        <td><code>save</code></td>
    </tr>
    <tr>
        <td>Edit</td>
        <td><code> edit SECRET_NAME </code></td>
    </tr>
    <tr>
        <td>Menu</td>
        <td><code>menu</code></td>
    </tr>
    <tr>
        <td> Exit </td>
        <td> <code>exit</code></td>
    </tr>
    <tr>
        <td> Cancel </td>
        <td> <code> c/ </code></td>
    </tr>
</table>

## Built With
Java 11

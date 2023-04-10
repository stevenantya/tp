# Steven - Project Portfolio Page

## Overview
SecureNUS is a desktop CLI application that provides secure secret management for NUS students.
SecureNUS is able to retain numerous types of secret such as credit card password,
wifi password, NUS Student ID secret, and many more!

The features of SecureNUS include adding, deleting, editing, listing, revealing, searching, 
and auto-save & auto-load your password!


1. Creation and Deletion of Password Fields
2. Different classes for different passwords

## Summary of Contributions

### Code contributed
- [RepoSense Link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=stevenantya)

### Enhancements implemented

<table class="tg">
<tbody>
  <tr>
    <td class="tg-0lax">Features</td>
    <td class="tg-0lax">Description</td>
    <td class="tg-0lax">Complexity</td>
  </tr>
  <tr>
    <td class="tg-0lax">App foundation</td>
    <td class="tg-0lax">The basic foundation of the app including SecureNUS, Ui (scanning and printing), Parser, Exception handlings.<br><br>Alongside with vital classes such as Secret parent class, Command parent abstract class<br><br>This is high in complexity because the app must be designed in a way it is easily scalable, understandable, and efficient.<br>The foundation went through several iterations of upgrade through the development.</td>
    <td class="tg-0lax">* * *</td>
  </tr>
  <tr>
    <td class="tg-0lax">Adding a password<br><i>new [o/OPTION] SECRET_NAME [f/FOLDER_NAME]</i>i></td>
    <td class="tg-0lax">Adding a new basic password, URL, StudentID, NUSNet passwords. The password names need to be unique. Verify if the adding is valid and exception handling</td>
    <td class="tg-0lax">* *</td>
  </tr>
  <tr>
    <td class="tg-0lax">Deleting passwords<br>`delete SECRET_NAME [SECRET_NAME2]`</td>
    <td class="tg-0lax">Deleting multiple or single password at a time. Verify if the deletion is valid and exception handling.</td>
    <td class="tg-0lax">* *</td>
  </tr>
  <tr>
    <td class="tg-0lax">Exit<br>`exit`</td>
    <td class="tg-0lax">Exiting the app.</td>
    <td class="tg-0lax">*</td>
  </tr>
</tbody>
</table>

### Contributions to the UG

### Contributions to the DG

### Contributions to team-based tasks

### Review/mentoring contributios
- [PR Reviews](https://github.com/AY2223S2-CS2113-T15-2/tp/pulls?q=reviewed-by%3Astevenantya)

### Contributions beyond the project team
- [Issues raised](https://github.com/stevenantya/ped/issues)
- [DG Reviewed](https://github.com/nus-cs2113-AY2223S2/tp/pull/10)
1.  Different password fields: Adding passwords

2.  Standard Password
    -   Name (MUST BE UNIQUE check and give error for duplicates)
    -   Username
    -   Password
    -   URI (e.g.): google.com

3.  Student ID

4.  NUSNet
5.  Delete Password (by name ONLY) 
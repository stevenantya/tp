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
    <td class="tg-0lax">The basic foundation of the app including SecureNUS, Ui (scanning and printing), Parser, 
        and Exception handlings. Alongside with vital classes such as Secret parent class, 
Command parent abstract class<br><br>This is high in complexity because the app must be 
designed in a way it is easily scalable, understandable, and efficient.<br>The foundation 
went through several iterations of upgrade through the development. Major changes was added during v2.0 and v2.1. The 
Class and Sequence Diagram in the <a href="https://ay2223s2-cs2113-t15-2.github.io/tp/DeveloperGuide.html#securenus-component">developer guide </a>is representative on how complex the app foundation is.</td>
    <td class="tg-0lax">* * *</td>
  </tr>
  <tr>
    <td class="tg-0lax">Adding a password<br></td>
    <td class="tg-0lax">Adding a new basic password, URL, StudentID, NUSNet passwords. 
        The password names need to be unique. Verify if the adding is valid and exception handling
        <br><br>This is medium complexity as there are different types of passwords, parsing handling, and exception handling for various errors
        such as invalid URL (not xxx.yyy), and integration to the Storage (SecretMaster).</td>
    <td class="tg-0lax">* *</td>
  </tr>
  <tr>
    <td class="tg-0lax">Deleting passwords<br></td>
    <td class="tg-0lax">Deleting multiple or single password at a time. Verify if the deletion is 
            valid and exception handling.<br><br>This is medium complexity because of parsing handling for multiple
        passwords and exception handling for deleting a invalid password or etc, and integrating with Storage (SecretMaster).</td>
    <td class="tg-0lax">* *</td>
  </tr>
  <tr>
    <td class="tg-0lax">Exit<br></td>
    <td class="tg-0lax">Exiting the app.<br><br>This function is easy to implement.</td>
    <td class="tg-0lax">*</td>
  </tr>
</tbody>
</table>

### Contributions to the UG (Including Extracts)
1. Contributed to the UG [Quick Start Guide](https://ay2223s2-cs2113-t15-2.github.io/tp/UserGuide.html#quick-start)
2. Contributed to the initial draft of UG in [google docs](https://docs.google.com/document/d/1p6DMV0FOJ1tTfo9iTrsbiq37AOpYOsLi8rKUksG0zSM/edit)
3. Set up the convention to follow in the UG, e.g. `new o/option f/folderName`

### Contributions to the DG (Including Extracts)
1. Contributed to the [implementation section](https://ay2223s2-cs2113-t15-2.github.io/tp/DeveloperGuide.html#implementation)
2. Created the UML class diagram for [SecureNUS class](https://ay2223s2-cs2113-t15-2.github.io/tp/DeveloperGuide.html#securenus-component)
3. Created UML sequence diagram for [adding basic password](https://ay2223s2-cs2113-t15-2.github.io/tp/DeveloperGuide.html#add-basic-password)
4. Fixed UML sequence diagram for [list password](https://ay2223s2-cs2113-t15-2.github.io/tp/DeveloperGuide.html#list-all-secrets) and [delete password](https://ay2223s2-cs2113-t15-2.github.io/tp/DeveloperGuide.html#delete-a-password)

### Contributions to team-based tasks
1. Setting up text-ui-test as we had issues during our first few versions
2. Setting up Checkstyle and fixing multiple checkstyle errors from other PRs
3. Maintain issues relating to the developer guides especially after week 13's reviews
4. [Released v2.0](https://github.com/AY2223S2-CS2113-T15-2/tp/releases/tag/v2.0)

### Review/mentoring contributions
- [PR Reviews](https://github.com/AY2223S2-CS2113-T15-2/tp/pulls?q=reviewed-by%3Astevenantya)

### Contributions beyond the project team
- [Issues raised](https://github.com/stevenantya/ped/issues)
- [DG Reviewed](https://github.com/nus-cs2113-AY2223S2/tp/pull/10)

## Selected key learning points
1. App foundation gone through many iterations because the previous versions are either incomplete or not structured properly (e.g. abstract class for Command, Secret parent class)
2. Working in a team with multiple PRs, enhancements, and issue at the same time needs a good teamwork and communication skills. This is especially relatable to Steven as the others will ask him on how the App Foundation works
3. One person's halt will have a domino effect on the others. It is advisable to help ones that are struggling.

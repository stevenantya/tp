# Hosea - Project Portfolio Page

## Overview
**SecureNUS**, a Command Line Interface (CLI) based password manager for students in National University of
Singapore (NUS) to store and refer to their passwords in a jiffy.

The application offers various functionalities such as creating, removing, displaying, finding, storing, and
retrieving passwords, as well as modifying password information.

### Summary of Contributions
- **New Feature**: Adding, Utilising of 2nd level secrets
    - These include: Crypto wallet, Credit Card, WiFi
    - What it does: Allows the user to create more different types secrets
    - Justification: This allows the user to create secrets with more variety that are typically used commonly in the 
daily life of a student
    - Highlights: This was challenging as it forced a lot more checking on everyone's parts to ensure all the operations
- **New Feature**: Operation Cancel
  - What it does: Cancel an Operation that requires multi line of input
  - Justification: This allows the user to stop creating a secret mid way, for example, because they input things wrong
or they did not want to save this password anymore
  - Highlights: This was challenging as I had to make sure it worked for all operations that require multi line input.
 However, this idea actually came up from User Acceptance Testing when I asked a friend to review the application.
 Among many other suggestions, this was chosen as it was critical to them yet somethings that could be adapted quickly. 
- Code Contributed: [Reposense Link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=t15&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=ollayf&tabRepo=AY2223S2-CS2113-T15-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
- Project Management: 
  - Managed releases v1.0 and v2.1
  - Tested and made ready for release
- Enhancement to other features:
  - More structure for Parsing commands,
  - More extensive checks for all commands before allowing the application use them
  - Data structures for our secrets
  - Creating the architecture of the application
  - QA Tests for all commands to ensure they work as per intended
  - Standardised the UI of the entire application
- Documentation:
  - User Guide
    - Did the first full draft of UG [(Pull Requests)](https://github.com/AY2223S2-CS2113-T15-2/tp/pull/66)
  - Developers' Guide
    - 
- Contribution to team based tasks:
- Setting up the GitHub team org/repo
1. Necessary general code enhancements 
2. Setting up tools e.g., GitHub, Gradle, text-ui-tests
3. Setting up gitflow and structure for all members
4. Maintaining the issue tracker
5. Release management
- Community:
  - PRs Reviewed: [#187](https://github.com/AY2223S2-CS2113-T15-2/tp/pull/181), [#171](https://github.com/AY2223S2-CS2113-T15-2/tp/pull/171),
[#54](https://github.com/AY2223S2-CS2113-T15-2/tp/pull/54) and more...
  - Reported Bugs (other than via telegram): [#92](https://github.com/AY2223S2-CS2113-T15-2/tp/issues/92), 
[#160](https://github.com/AY2223S2-CS2113-T15-2/tp/issues/160), [#84](https://github.com/AY2223S2-CS2113-T15-2/tp/issues/84) and many more...
  - Created the important development structures for my team including the inheritance structure of Commands and Data 
structures
- Tools: 
  - Ensured the working of (UI) system with scanner
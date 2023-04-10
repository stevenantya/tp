# Khairul Aizat - Project Portfolio Page

## Overview
SecureNUS is a desktop CLI app for managing and storing passwords. Its features include adding, deleting, listing, revealing, searching, saving and loading passwords, and editing password details. The app is simple and efficient to use, and users can access a menu function for command summaries and syntax.



### Summary of Contributions

#### Code contributed

- [RepoSense Link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=kairuler&breakdown=true)

| Feature                           | Difficulty | Description                                                                                                                                                                                                                                                                                                           |
|-----------------------------------|:----------:|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Revealing a password `view`       |     ★      | To maintain security when listing passwords, the actual passwords will not be revealed. To reveal a specific password, the user inputs the name of the password to view. If the password name does not match any stored password, an error message is printed.                                                        |
| Searching for a password `search` |     ★★     | Searches for passwords with names that contain the specified name, and optionally in the specified folder. If a folder name is provided, it searches in that specific folder, otherwise it searches in all passwords. The search results are printed out in a formatted table along with the number of matches found. |
| Edit a password `edit`            |    ★★★     | To edit a password, the user inputs the name of the password, and optionally the new name and new folder of the password. The user then inputs the new values of the relevant fields to be updated (e.g. password, credit card number, NUSnet ID, etc.).                                                              |                                                                                                                                                                                                                                                                                                                       |

#### Enhancements implemented:

- Wrote and tested ~50% of JUnit tests whilst ensuring quality and efficiency of test cases. Achieved >80% Method coverage. [(RepoSense)](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=t15-2&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=test-code&since=2023-02-17&tabOpen=false&zFR=false)
- Beautified the table output of `Search` command. [(#222)](https://github.com/AY2223S2-CS2113-T15-2/tp/pull/222)

#### Major difficulties faced:
- There are many variations of editable fields based on the type of Secret. Handling all of them separately was challenging.
- Handling JUnit tests for methods that require user input.

#### Contributions to the UG:

- Contributed to initial draft of UG.

#### Contributions to the DG:
- Created UML Sequence diagrams for Implementation segment (`add`, `delete` and `list` features)
- Wrote Appendices A, B, C, D and E (Product Scope, User Stories, Non-Functional Requirements, Glossary and Instructions for manual testing)

#### Contributions to team-based tasks:
- Set up Team's GitHub organisation and `tP` repository.
- Maintain the issue tracker by ensuring each Issue/PR had the correct `Assignees`, `Labels` and `Milestone` tags.
- Heavily involved in the triage of bugs from the PE Dry-Run. Assigned and/or resolved >30 bugs. [(#103-148, etc.)](https://github.com/AY2223S2-CS2113-T15-2/tp/issues?page=1&q=commenter%3Akairuler)
- Performed penetration testing for `add`, `delete` and `list` features. [(#201](https://github.com/AY2223S2-CS2113-T15-2/tp/issues/201), [#206)](https://github.com/AY2223S2-CS2113-T15-2/tp/issues/206)

#### Review/mentoring contributions:
- [See my team's PRs that I reviewed](https://github.com/AY2223S2-CS2113-T15-2/tp/pulls?q=reviewed-by%3Akairuler)
                                             
#### Contributions beyond the project team:
- [See my review of another team's Developer Guide](https://github.com/nus-cs2113-AY2223S2/tp/pull/23/files)

- [See my review of other teams' tP product](https://github.com/kairuler/ped/issues)

#### Contributions to the Developer Guide (Extracts): 
- Refer to Implementation segment and appendices A, B, C, D and E (Product Scope, User Stories, Non-Functional Requirements, Glossary and Instructions for manual testing) of our DG.

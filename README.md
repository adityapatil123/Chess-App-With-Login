# Chess-App-With-Login
Chess app with login and signup using React, Kotlin and  mySQL

## Prerequisites:
- npm
- React.js
- Chrome/Mozilla browser
- Kotlin and Intellij/Android Studio
- mySQL

## Usage:
Connection here is **ReactJS <=> Kotlin <=> MySQL**
### ReactJS:
- App is made using **Webpack** module bundler - [Webpack complete setup](https://github.com/adityapatil123/Hello-App-React-Typescript-with-Webpack)
- 3 pages: Login, SignUp and User page.
- In User page, 2 componensts **OthernUsers** and **RequestBoard**(In which there will  be **Chessboard**)
- **Redux** is used for state management.
- **SemanticsUI** is used for styling.
- Simple authentication is used, one can go for Auth and JWT tokens.
- POST requests are made (which will be handled by Kotlin).

### Kotlin:
- **Jetty** server is used on port 8888.
- POST requets are handled here.
- SQL queries are made as per request. Database will be updated accordingly.
- After starting of match, chessboard will be created in kotlin, where conditions will be  checked if coin can be moved or not.

### MySQL:
- 3 tables: Person, Request and MatchHistory. Their description:
```
mysql> describe Person;
+-----------+-------------+------+-----+---------+-------+
| Field     | Type        | Null | Key | Default | Extra |
+-----------+-------------+------+-----+---------+-------+
| UserID    | int(11)     | NO   | PRI | NULL    |       |
| FirstName | varchar(30) | NO   |     | NULL    |       |
| LastName  | varchar(30) | YES  |     | NULL    |       |
| EmailID   | varchar(50) | NO   |     | NULL    |       |
| UserName  | varchar(30) | NO   | UNI | NULL    |       |
| Password  | varchar(30) | NO   |     | NULL    |       |
| IsActive  | tinyint(1)  | YES  |     | NULL    |       |
| IsFree    | tinyint(1)  | YES  |     | NULL    |       |
+-----------+-------------+------+-----+---------+-------+
8 rows in set (0.02 sec)

mysql> describe Request;
+-------------------+------------+------+-----+---------+----------------+
| Field             | Type       | Null | Key | Default | Extra          |
+-------------------+------------+------+-----+---------+----------------+
| RequestID         | int(11)    | NO   | PRI | NULL    | auto_increment |
| SenderID          | int(11)    | NO   |     | NULL    |                |
| ReceiverID        | int(11)    | NO   |     | NULL    |                |
| IsRequestActive   | tinyint(1) | YES  |     | NULL    |                |
| IsRequestAccepted | tinyint(1) | YES  |     | NULL    |                |
+-------------------+------------+------+-----+---------+----------------+
5 rows in set (0.00 sec)

mysql> describe MatchHistory;
+---------------+------------+------+-----+-------------------+-----------------------------+
| Field         | Type       | Null | Key | Default           | Extra                       |
+---------------+------------+------+-----+-------------------+-----------------------------+
| ID            | int(11)    | NO   | PRI | NULL              | auto_increment              |
| RequestID     | int(11)    | NO   |     | NULL              |                             |
| MatchID       | int(11)    | NO   |     | NULL              |                             |
| IsMatchActive | tinyint(1) | YES  |     | NULL              |                             |
| MoveID        | int(11)    | NO   |     | NULL              |                             |
| Color         | varchar(1) | NO   |     | NULL              |                             |
| LastUpdated   | timestamp  | NO   |     | CURRENT_TIMESTAMP | on update CURRENT_TIMESTAMP |
| P1            | int(11)    | NO   |     | NULL              |                             |
| P2            | int(11)    | NO   |     | NULL              |                             |
| P3            | int(11)    | NO   |     | NULL              |                             |
| P4            | int(11)    | NO   |     | NULL              |                             |
| P5            | int(11)    | NO   |     | NULL              |                             |
| P6            | int(11)    | NO   |     | NULL              |                             |
| P7            | int(11)    | NO   |     | NULL              |                             |
| P8            | int(11)    | NO   |     | NULL              |                             |
| R1            | int(11)    | NO   |     | NULL              |                             |
| K1            | int(11)    | NO   |     | NULL              |                             |
| B1            | int(11)    | NO   |     | NULL              |                             |
| S1            | int(11)    | NO   |     | NULL              |                             |
| Q1            | int(11)    | NO   |     | NULL              |                             |
| B2            | int(11)    | NO   |     | NULL              |                             |
| K2            | int(11)    | NO   |     | NULL              |                             |
| R2            | int(11)    | NO   |     | NULL              |                             |
+---------------+------------+------+-----+-------------------+-----------------------------+

```

- In MatchHistory, for each move there will be an entry in table. So we can have MatchReplay as well.

## How to Play:
User will **Login** if he/she has account, otherwise by starting with creating one account using **Signup**.
- After login, user will see Profile page, where on left side-bar list of users, in which
1. **Green** will be if that user is online and free to start game.
2. **Orange** if user is online but busy in another game.
3. **Red** means user is offline.
- Right of side-bar i.e. main page,
1. On start, there will will be information page about playing.
2. You can click any user on side-bar for sending or receiving the request sent by him/her.
3. If alredy request is there you can start by clicking receive button, otherwise send one request and wait to get request accepted.
4. Sending one will have White color.
5. Game will be finished if anyone of the kings dies.

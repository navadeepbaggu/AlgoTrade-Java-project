# AlgoTrade

**A Java stock trading simulation and market analytics project**

AlgoTrade is a menu-driven Java console application that demonstrates common stock market and portfolio workflows. Users can register, explore sample stocks, simulate trades, and review portfolio and transaction information.

> **Project scope:** This is an educational simulation. It does not connect to a live stock exchange or place real trades.

## Features

- Register and log in to an account
- Browse sample stocks and search by symbol or company name
- Simulate buying and selling shares with confirmation
- Add multiple stocks to a buy basket
- View portfolio holdings, reports, and analytics
- Review transaction history
- Manage a watchlist and sample-price alerts
- Save supported account and investor data to local files

## Technologies

- Java 21
- Eclipse IDE
- Java collections and file handling
- No Maven dependencies

## Project structure

```text
src/
├── alerts/     Price alert classes and alert management
├── app/        Application entry point
├── auth/       User accounts, login, and password handling
├── investor/   Investor, portfolio, holdings, and watchlist
├── market/     Market assets, stocks, and stock search
├── reports/    Reporting interface
├── storage/    Account, investor, and transaction file storage
├── trading/    Broker, trading operations, baskets, and transactions
└── ui/         Console menus

The application starts from app.LoginDemo.

Run the project in Eclipse
1. Clone or download this repository.
2. In Eclipse, select File → Import → Existing Projects into Workspace.
3. Select the folder containing the project and complete the import.
4. Make sure the project uses Java 21.
5. Open src/app/LoginDemo.java.
6. Right-click the file and choose Run As → Java Application.


Local data storage
The storage classes save supported user and investment data in local files under:
D:/AlgoTradeData/

The application may need that location to exist and be writable. This path is currently set in the source code, so it may need adjustment on computers that do not have a D: drive. Local account and transaction data should not be committed to GitHub.
Java concepts demonstrated
- Classes, objects, encapsulation, and constructors
- Inheritance, abstract classes, and method overriding
- Interfaces and functional interfaces
- Collections, including ArrayList, Comparable, and Comparator
- Lambdas and streams
- Exception handling and regular expressions
- File reading and writing
- Association, aggregation, and composition


Limitations
- Stock prices are sample values, not live market data.
- Trades are simulated and do not use real money.
- Price alerts are checked against sample prices; they are not live notifications.
- Data is stored in local files rather than a database.
- The Java application uses a console interface.

Team
Developed as a student hackathon project.

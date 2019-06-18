# Chess-App-With-Login
Chess app with login and signup using React, Kotlin and  mySQL

## Prerequisites:
- npm
- React.js
- Chrome/Mozilla browser
- Kotlin and Intellij/Android Studio
- mySQL

## Usage:
We will be using React for the front-end and Kotlin as back-end with the mysql databases.

## Step 1: Simple build using Webpack and Runnning the app
If you know this, then well and good. If not, [Follow part1](https://github.com/adityapatil123/Hello-App-React-Typescript-with-Webpack)

## Step 2: Make 3 pages, Login, SignUp and User
As  we are going to make  multi-page app, we will be needing React-Router for routing.
```bash
$ npm i react-router
$ npm i @types/react-router
```
And for the better state management, we will be using Redux(this is optional)
```bash
$ npm install redux
```
In **App.tsx**,which is rendered from **index.tsx**
```typescript
render() {
    return (
      <BrowserRouter>
        <Switch>
          <Route path="/" exact component={Login} />
          <Route path="/signup" component={SignUp} />
          <PrivateRoute path="/user" />
        </Switch>
      </BrowserRouter>
    );
  }
```
We have 3 paths for 3 pages.We can go to Login and Signup page without authentication. 
But one can't visit to User page without typing username and password.
Hence, we have created and used **PrivatePath**.
```typescript
const PrivateRoute = ({ ...rest }) => (
  <Route {...rest} render={(props) => (
    store.getState().isActive === true
      ? <UserHome />
      : <div><Redirect to='/' />{alert("Login Required")}</div>
  )} />
)
```
Here, I have used simple authentication, one can go for Auth and JWT Tokens.
Then, make three pages with react.For styling, I have used **SemanticsUI**.Its easy to use and powerful.

## Step 3: Start Kotlin and mySQL.
Done with 3 pages, but these pages are not doing anything.We will connect them BackEnd
- Connection:  React <=> Kotlin <=> mySQL
- **Kotlin :*** I've used  **ktor** framework and Jetty server at port 8888.
    Try 'GET' and other requests for basic starting.

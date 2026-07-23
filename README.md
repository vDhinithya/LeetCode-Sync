# 🚀 LeetCode Sync Automation

Automatically sync your accepted LeetCode submissions to GitHub using Java and GitHub Actions.

This project authenticates with LeetCode using your session cookie, fetches your latest accepted submissions through the GraphQL API, downloads the source code, and stores them in your repository automatically.

---

## ✨ Features

* 🔄 Automatically fetches recent accepted LeetCode submissions
* 📂 Saves solutions in a structured directory
* ☕ Built entirely with Java 17
* ⚡ Uses LeetCode GraphQL API
* 🤖 Runs automatically every 6 hours via GitHub Actions
* 🔒 Keeps credentials secure using GitHub Secrets
* 🚫 Prevents duplicate solution files
* 📈 Maintains a public coding activity repository

---

## 🏗️ Project Structure

```text
LeetCode-Sync/
│
├── .github/
│   └── workflows/
│       └── sync.yml
│
├── Daily-Practice/
│   ├── two-sum.java
│   ├── reverse-integer.java
│   └── ...
│
├── src/
│   └── main/
│       └── java/
│           └── LeetCodeSync.java
│
├── pom.xml
└── README.md
```

---

## ⚙️ How It Works

### Step 1: Authentication

The application reads your LeetCode session cookie from:

```bash
LEETCODE_SESSION
```

stored securely in GitHub Secrets.

---

### Step 2: Fetch User Information

A GraphQL query is sent to:

```text
https://leetcode.com/graphql
```

to retrieve your username.

---

### Step 3: Retrieve Recent Accepted Submissions

The application requests your latest accepted submissions:

```graphql
query recentAcSubmissions {
    recentAcSubmissionList
}
```

---

### Step 4: Fetch Submission Details

For every accepted submission:

* Submission ID
* Status
* Language
* Source Code

are fetched using LeetCode's GraphQL API.

---

### Step 5: Save Solutions

Solutions are stored under:

```text
Daily-Practice/
```

Example:

```text
Daily-Practice/
├── two-sum.java
├── valid-parentheses.java
├── merge-two-sorted-lists.java
```

---

### Step 6: Commit and Push

GitHub Actions automatically:

```bash
git add .
git commit
git push
```

when new solutions are found.

---

## 🔧 GitHub Actions Workflow

The workflow executes:

```yaml
schedule:
  - cron: '0 */6 * * *'
```

which means:

* Every 6 hours
* Or manually via GitHub Actions

Manual execution:

```text
Actions
→ Sync LeetCode Submissions
→ Run Workflow
```

---

## 🔐 Setting Up Secrets

Navigate to:

```text
Repository Settings
→ Secrets and Variables
→ Actions
→ New Repository Secret
```

Create:

| Name             | Value                        |
| ---------------- | ---------------------------- |
| LEETCODE_SESSION | Your LeetCode session cookie |

---

## 🛠️ Tech Stack

| Technology           | Purpose               |
| -------------------- | --------------------- |
| Java 17              | Core application      |
| Maven                | Dependency Management |
| Jackson              | JSON Parsing          |
| Java HttpClient      | API Communication     |
| GitHub Actions       | Automation            |
| LeetCode GraphQL API | Data Source           |

---

## 🚀 Running Locally

Clone the repository:

```bash
git clone https://github.com/your-username/LeetCode-Sync.git
```

Move into the project:

```bash
cd LeetCode-Sync
```

Set environment variable:

### Windows PowerShell

```powershell
$env:LEETCODE_SESSION="your_session_cookie"
```

### Linux / macOS

```bash
export LEETCODE_SESSION="your_session_cookie"
```

Run:

```bash
mvn clean compile exec:java -Dexec.mainClass="LeetCodeSync"
```

---

##  Sample Output

```text
Successfully authenticated as: johndoe

Found 10 recent submissions.

Checking: two-sum
 -> Status found: Accepted
 -> SUCCESS: Synced two-sum

Checking: reverse-integer
 -> Status found: Accepted
 -> SUCCESS: Synced reverse-integer
```

---

##  Security Notes

* Never commit your LeetCode session cookie.
* Always store credentials in GitHub Secrets.
* Rotate your session cookie if it becomes exposed.
* This project only performs read operations on your LeetCode account.

---

## 📈 Future Improvements

* Dynamic commit messages based on solved problems
* Auto-generated README statistics
* Problem difficulty badges
* Language-specific folders
* Daily challenge tracking
* LeetCode profile analytics dashboard
* Submission metadata (runtime and memory usage)

---

## 🤝 Contributing

Contributions, improvements, and feature suggestions are welcome.

Feel free to fork the repository and submit a pull request.

---

### ⭐ If this project helped you, consider giving it a star!

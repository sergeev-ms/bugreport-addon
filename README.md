# Bug report add-on for CUBA platform 
Add the button to the system ExceptionDialog which send an email with stacktrace to developer.


## Installation

1. `bugreport` is available via github packages, just add repositiry to your `build.gradle`:
```
        maven {
            url 'https://maven.pkg.github.com/sergeev-ms/maven'
            credentials{
                username(project.findProperty("github.user") ?: System.getenv("GITHUB_USER"))
                password(project.findProperty("github.key") ?: System.getenv("GITHUB_TOKEN"))
            }
        }
```

2. Select a version of the add-on which is compatible with the platform version used in your project:

| Platform Version | Add-on Version |
| ---------------- | -------------- |
| 7.2.x            | 0.4.x          |
| 7.1.x            | 0.3         |


Add custom application component to your project:

* Artifact group: `com.borets.bugreport`
* Artifact name: `bug-global`
* Version: *add-on version*






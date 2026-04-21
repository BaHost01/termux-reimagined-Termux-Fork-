# Termux Project Context

This project is the Termux Android application, a terminal emulator and Linux environment for Android. It is a fork of the original Termux app, identified as "termux-reimagined".

## Project Overview

- **Core Functionality:** Provides a terminal interface and a minimal Linux base system on Android.
- **Main Technologies:** Java (Android), C/C++ (NDK), Gradle.
- **Architecture:**
  - `app`: The main Android application module, containing UI (`TermuxActivity`, `ControlCenterActivity`, `MacroManagerActivity`) and service logic (`TermuxService`).
  - `termux-shared`: A library containing shared constants, utilities, and classes used by Termux and its plugins.
  - `terminal-emulator`: The core terminal emulation logic (independent of Android UI).
  - `terminal-view`: An Android View widget that renders the terminal and handles touch/key input.
- **Key Components:**
  - `TermuxActivity`: The primary UI entry point, now featuring a **Floating Session Overlay**.
  - `ControlCenterActivity`: A centralized hub for quick toggles and configuration.
  - `MacroManagerActivity`: Documentation and management for terminal macros.
  - `TermuxService`: A foreground service that manages terminal sessions.

## UI & Input Overhaul (2026 Update)

- **Material Design 3:** The app uses MD3 themes, colors, and components (XML-based).
- **Floating Session Overlay:** Replaced the traditional sidebar drawer with a draggable, draggable Material Card overlay for session management. Toggle via the `OVERLAY` extra key or navigation shortcuts.
- **Advanced Macros:** Enhanced macro engine in `TerminalExtraKeys` supports `\n` for automated command execution.
- **Improved Modifier Handling:** Refined `TerminalView` logic for better hardware keyboard support (Ctrl, Alt, Meta).
- **Control Center:** Quick access to Wakelock, Screen On, Styling, and Macros. Long-press the settings icon in the overlay to launch.

## Building and Running

The project uses Gradle as its build system.

### Key Commands

- **Build Debug APK:** `./gradlew assembleDebug`
- **Build Release APK:** `./gradlew assembleRelease` (Note: requires signing configuration)
- **Run Unit Tests:** `./gradlew test`
- **Clean Project:** `./gradlew clean`
- **Download Bootstrap Packages:** `./gradlew downloadBootstraps` (This is automatically called during the build process).

### Configuration

- **Minimum SDK:** 21 (Android 5.0)
- **Target SDK:** 28 (Android 9.0) - Kept at 28 to maintain functionality that newer Android versions restrict (like `exec()` in app data).
- **Compile SDK:** 36
- **Package Name:** `com.termux` (defined in `TermuxConstants` and `build.gradle`).

## Development Conventions

- **No Hardcoded Paths:** NEVER use hardcoded paths like `/data/data/com.termux/files/usr`. Always use constants from `com.termux.shared.termux.TermuxConstants`.
- **Commit Messages:** Must follow [Conventional Commits](https://www.conventionalcommits.org/). Types should be capitalized (e.g., `Added:`, `Fixed:`, `Changed!:` for breaking changes).
- **Versioning:** Follows [Semantic Versioning 2.0.0](https://semver.org/).
- **Shared UID:** All Termux apps and plugins use `sharedUserId="com.termux"` and must be signed with the same key to interact.
- **Logging:** Use `com.termux.shared.logger.Logger`. Log levels can be adjusted in app settings for debugging.

## Key Files for Reference

- `README.md`: High-level overview and installation instructions.
- `termux-shared/src/main/java/com/termux/shared/termux/TermuxConstants.java`: The source of truth for all project-wide constants.
- `app/build.gradle`: Main application build configuration and bootstrap download logic.
- `app/src/main/java/com/termux/app/TermuxActivity.java`: Main UI logic.
- `app/src/main/java/com/termux/app/TermuxService.java`: Core service managing terminal sessions.
- `terminal-emulator/src/main/java/com/termux/terminal/TerminalSession.java`: Represents a single terminal session.

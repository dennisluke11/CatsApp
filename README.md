# 🐱 Cat Gallery App

An Android app built with **Jetpack Compose**, **MVVM**, **Room**, **Retrofit**, and **Coil**. The app displays a gallery of cat images from [TheCatAPI](https://thecatapi.com), with offline caching and support for device rotation.

---

## 🚀 Features

- ✅ Fetch 100+ cat images from a public API (JSON)
- ✅ Save data in local Room database
- ✅ Load from local DB after first fetch
- ✅ Image caching using Coil
- ✅ Offline-first design — works without internet after initial fetch
- ✅ Connectivity check with dialog prompt
- ✅ Compose UI that survives rotation
- ✅ Click on a cat to view a detail screen

---

## 🛠️ Tech Stack

| Layer          | Library         |
|----------------|-----------------|
| UI             | Jetpack Compose |
| State Mgmt     | ViewModel + StateFlow |
| Networking     | Retrofit + OkHttp |
| Image Loading  | Coil            |
| Database       | Room            |
| DI             | Koin            |
| Tools          | Kotlin, AndroidX |

---

## 🏗️ Architecture
MVVM (Model-View-ViewModel)
│
├── data
│ ├── local (Room)
│ │ ├── CatDao.kt
│ │ ├── CatDatabase.kt
│ │ └── CatEntity.kt
│ ├── remote (Retrofit)
│ │ ├── CatApiService.kt
│ │ └── CatApiItem.kt
│ └── CatRepository.kt
│
├── ui
│ ├── CatListScreen.kt
│ └── CatDetailScreen.kt
│
├── viewmodel
│ └── CatViewModel.kt
│
└── di
└── AppModule.kt (Koin)


---

## 📲 Screenshots

| Home | Details |
|------|---------|
| ![Gallery](https://github.com/user-attachments/assets/4b08f31f-3727-4ae4-a52e-10401cd6aa73) | ![Details](https://github.com/user-attachments/assets/519e8082-be49-4df7-a060-be8f827d492e) |

---

## 🧰 Tech Stack

- Jetpack Compose (UI)
- Kotlin Coroutines
- Retrofit + Gson
- Room (SQLite)
- Coil (Image loading + caching)
- Koin (Dependency Injection)

---

## Set Up the Project

Follow these simple steps to get the CatsApp ready:

Clone the Repository:
First, open your terminal (on macOS/Linux) or Git Bash/Command Prompt (on Windows). Navigate to where you want to save the project (e.g., your "Documents" folder) and run this command:

Bash

git clone https://github.com/dennisluke11/CatsApp.git
This command downloads all the project files to your computer.

Navigate into the Project Folder:
Once the cloning is complete, move into the newly created CatsApp directory:

Bash

cd CatsApp
Open in Android Studio:
Now, open Android Studio. From the welcome screen, select "Open an existing Android Studio project" and then browse to the CatsApp folder you just cloned. Select the folder and click "Open."

Wait for Gradle Sync:
Android Studio will now take some time to "sync Gradle." This means it's setting up the project, downloading necessary components, and resolving dependencies. You'll see a progress bar or messages in the bottom status bar. This step is crucial and may take a few minutes, especially the first time. Please wait for it to complete.

▶️ Run the App
Once Gradle sync is finished, you're ready to run the app!

Connect a Device or Use an Emulator:

Physical Device: Connect an Android phone to your computer via USB and ensure USB Debugging is enabled (you might need to enable Developer Options on your device).
Emulator: In Android Studio, go to Tools > Device Manager to create or start a virtual Android device (emulator).
Run:


Click the green play button (▶️) in the toolbar at the top of Android Studio. Choose your connected device or emulator when prompted.

## 🔌 API Reference

**TheCatAPI**
- Endpoint: `https://api.thecatapi.com/v1/images/search`
- Params: `?limit=100&mime_types=jpg,png`

Response:
```json
[
  {
    "id": "abc123",
    "url": "https://cdn2.thecatapi.com/images/abc123.jpg"
  }
]


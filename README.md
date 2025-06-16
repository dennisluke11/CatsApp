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
| ![Gallery](https://placehold.it/300x600?text=Cat+Gallery) | ![Details](https://placehold.it/300x600?text=Cat+Details) |

---

## 🧰 Tech Stack

- Jetpack Compose (UI)
- Kotlin Coroutines
- Retrofit + Gson
- Room (SQLite)
- Coil (Image loading + caching)
- Koin (Dependency Injection)

---

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

🛠️ Getting Started

1. Clone the repository
git clone https://github.com/dennisluke11/CatsApp.git
cd CatsApp

2. Open in Android Studio

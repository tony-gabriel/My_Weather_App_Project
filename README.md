# My Weather App Project

A modern Android weather application built using MVVM architecture, Jetpack Compose for UI, and Hilt for dependency injection. The app leverages the OpenWeatherMap API to provide up-to-date weather information for cities worldwide, with features designed for a smooth and interactive user experience.

## Features

- **Current Weather Search**: Enter any city name to retrieve real-time weather details including temperature, humidity, and weather description.
- **Favorite City Support**: Save your favorite city for quick access; the app automatically displays weather information for your saved location upon launch.
- **Beautiful UI**: Built with Jetpack Compose, featuring animated splash screens, themed gradients, and informative cards for weather details.
- **MVVM Architecture**: Clean separation of concerns using ViewModel, Repository, and UseCase layers.
- **Dependency Injection**: Powered by Hilt for robust and testable dependency management.
- **Error Handling**: Graceful responses to missing city, network, and unexpected errors.
- **Persistence**: Stores favorite city using Android DataStore.
- **Material 3**: Consistent and modern design using Material 3 components.


## Getting Started

### Prerequisites

- [Android Studio](https://developer.android.com/studio) (Giraffe or newer recommended)
- Android device or emulator running API level 23+
- Internet connectivity for API access

### Setup Instructions

1. **Clone the repository**:
   ```bash
   git clone https://github.com/tony-gabriel/My_Weather_App_Project.git
   cd My_Weather_App_Project
   ```

2. **Obtain OpenWeatherMap API Key**:
   - Sign up at [openweathermap.org](https://openweathermap.org/) and generate a free API key.

3. **Configure API key**:
   - Open the `local.properties` file in the project root directory (create it if it doesn't exist).
   - Add your API key:
     ```
     apiKey=YOUR_API_KEY_HERE
     ```

4. **Build and Run**:
   - Sync Gradle and run the app on your preferred device or emulator.

## How It Works

- **Search a City**: Type the city name and tap "Get Weather". The app fetches the weather data from OpenWeatherMap and displays it with friendly icons and metrics.
- **Favorite City**: Click the heart icon to save the current city as your favorite. The app remembers your choice and shows its weather automatically on startup.
- **Error Handling**: If a city isn't found or there's a network issue, the app will notify you with clear error messages.

## Architecture

- **MVVM**: All business logic is handled in ViewModels and UseCases. The UI is reactive and updates automatically based on state changes.
- **Hilt DI**: All dependencies (API service, repository, preferences) are injected via Hilt modules.
- **Jetpack Compose**: UI is declarative, responsive, and easy to customize.

## Dependencies

- [Retrofit](https://square.github.io/retrofit/) (networking)
- [OkHttp](https://square.github.io/okhttp/) (HTTP client)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Hilt](https://dagger.dev/hilt/)
- [Android DataStore](https://developer.android.com/topic/libraries/architecture/datastore) (for persistence)
- [OpenWeatherMap API](https://openweathermap.org/api)

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request for consideration.

---

**Author:** [Tony Gabriel](https://github.com/tony-gabriel)

---

### Acknowledgments

- OpenWeatherMap for the weather data API
- Jetpack Compose and Material 3 teams for modern Android UI components

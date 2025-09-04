# 🦁 Zuuki - Animal Inventory Management System

[![Java](https://img.shields.io/badge/Java-17%2B-orange.svg)](https://www.oracle.com/java/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Console App](https://img.shields.io/badge/Type-Console%20Application-blue.svg)]()

> A simple yet powerful console-based animal inventory management system for zoos, wildlife sanctuaries, and animal enthusiasts.

## 📋 Table of Contents

- [About](#about)
- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Running the Application](#running-the-application)
- [Usage](#usage)
  - [Main Menu](#main-menu)
  - [Adding Animals](#adding-animals)
  - [Searching and Filtering](#searching-and-filtering)
- [Project Structure](#project-structure)
- [Design Decisions](#design-decisions)
- [Contributing](#contributing)
- [Future Enhancements](#future-enhancements)
- [License](#license)
- [Contact](#contact)

## 🎯 About

Zuuki is a lightweight, console-based animal inventory management system designed to help manage animal collections efficiently. Whether you're running a small wildlife sanctuary or managing a large zoo, Zuuki provides essential CRUD operations with an intuitive command-line interface.

### Why Zuuki?

- **Simple**: No complex setup or database requirements
- **Fast**: Lightweight console application with instant responses  
- **Flexible**: Support for various animal species and enclosure management
- **Educational**: Great example of object-oriented programming principles in Java

## ✨ Features

### Core Functionality
- 🐾 **Animal Management**: Add, update, delete, and view animal records
- 🔍 **Smart Search**: Find animals by name, species, or enclosure location
- 📊 **Analytics**: Count animals by species or enclosure for inventory tracking
- 🏠 **Enclosure Management**: Organize animals across 5 different enclosures (0-4)

### Technical Features
- **Input Validation**: Robust error handling and data validation
- **Memory Efficient**: Uses parallel arrays for optimal performance
- **User-Friendly**: Clear prompts and formatted output
- **Extensible**: Well-documented code ready for feature additions

## 🚀 Getting Started

### Prerequisites

- **Java 17 or higher** - [Download here](https://www.oracle.com/java/technologies/javase-downloads.html)
- **Command line/Terminal** access
- **Text editor** or IDE (optional, for viewing source code)

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/zuuki.git
   cd zuuki
   ```

2. **Compile the Java source**
   ```bash
   javac src/ZuukiApp.java
   ```

3. **Verify compilation**
   ```bash
   # Check that ZuukiApp.class was created
   ls src/ZuukiApp.class
   ```

### Running the Application

```bash
# Navigate to src directory
cd src

# Run the application
java ZuukiApp
```

## 📖 Usage

### Main Menu

When you start Zuuki, you'll see the main menu:

```
Welcome to Zuuki <3.
Your personal animal inventory app.
Please choose.

1. Add
2. Update  
3. Display
4. Delete
5. Quit
>> 
```

### Adding Animals

Select option `1` to add a new animal:

```
Adding animal..

Name:
>> Leo

Age: 
>> 5

Species:
>> Lion

Enclosure (0-4): 
>> 2

Animal added successfully!
```

### Searching and Filtering

The Display menu (option `3`) offers several ways to view your animals:

#### Search by Name
```
Name to find: 
>> Leo

Index: 0
Name: Leo
Age: 5
Species: Lion
Enclosure: 2
------------------
```

#### Browse by Species
- View all registered species
- Display all animals of a specific species
- Count animals by species

#### Browse by Enclosure
- Display all animals in a specific enclosure
- Count animals per enclosure for capacity planning

### Sample Session

```
Welcome to Zuuki <3.
Your personal animal inventory app.

1. Add
2. Update
3. Display
4. Delete
5. Quit
>> 1

Adding animal..
Name: >> Simba
Age: >> 3
Species: >> Lion
Enclosure (0-4): >> 1

Animal added successfully!

>> 3
Display options:
1. Name
2. Species  
3. Enclosure
4. Back
>> 2

Species Registered: 
Lion | 

1. Display
2. Count
3. Back
>> 2

Species to count: >> Lion
A total of 1 animal/s were found with species of Lion.
```

## 📁 Project Structure

```
zuuki/
├── src/
│   └── ZuukiApp.java          # Main application file
├── docs/
│   └── project-plan.md        # Project planning documents
├── README.md                  # This file
└── LICENSE                    # MIT License
```

### Key Components

- **`ZuukiApp.java`** - Main application with all functionality
- **Parallel Arrays** - Efficient data storage for animal attributes
- **Input Validation** - Robust user input handling and error management
- **Menu System** - Intuitive navigation through application features

## 🏗️ Design Decisions

### Why Parallel Arrays?
- **Simplicity**: No need for custom classes or complex data structures
- **Performance**: Direct array access with O(1) lookup time
- **Memory Efficient**: Minimal overhead compared to object-oriented approaches
- **Educational**: Demonstrates fundamental data structure concepts

### Why Console Interface?
- **Universal Compatibility**: Works on any system with Java
- **No Dependencies**: No GUI libraries or frameworks required
- **Fast Development**: Focus on core functionality over UI design
- **Resource Efficient**: Minimal system requirements

### Method Organization
- **Single Responsibility**: Each method has one clear purpose
- **Input Validation**: Centralized through `readString()` and `readInt()` methods
- **Error Handling**: Graceful handling of invalid inputs and edge cases
- **Code Reuse**: Common operations abstracted into utility methods

## 🤝 Contributing

We welcome contributions! Here's how you can help:

### Getting Started
1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Development Guidelines
- Follow existing code style and conventions
- Add JavaDoc comments for new methods
- Include input validation for user-facing features
- Test edge cases and error conditions
- Update documentation for new features

### Code Style
- **Naming**: Use clear, descriptive variable and method names
- **Comments**: Explain complex algorithms and business logic
- **Formatting**: Follow standard Java formatting conventions
- **Error Handling**: Provide clear error messages and graceful recovery




## 📋 Project Planning

For detailed project planning and system design, check out our [interactive project plan](https://excalidraw.com/#json=14WxR2giqnqLC67d1z0SV,j1zxeewgo3UKgokDwEAARg).

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

```
MIT License

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
```





<div align="center">
  
**⭐ If you found this project helpful, please give it a star! ⭐**

Made with ❤️ and ☕ 

[⬆ Back to Top](#-zuuki---animal-inventory-management-system)

</div>

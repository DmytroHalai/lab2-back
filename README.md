# Lab2-Back
### Варіант 1, група ІМ-31

**Lab2-Back** — бекенд-сервіс на базі Spring Boot (Maven) для управління користувачами, категоріями та записами (records).  

З цікавого - реалізовано транзактивну залежність між сутностями User -> Category -> Record. Видаляючи юзера - видаляються усі поєднані з ним категорії та рекорди. При створенні рекорду без зазначення валюти - береться валюта юзера по замовчуванню

Реалізовані REST-ендпоінти: створення, отримання, видалення.  
Документація API — через springdoc‑openapi (Swagger/OpenAPI).

Посилання на задеплоєний проєкт - https://cost-accounter-pro.onrender.com/users

---

## Основні можливості

- Керування користувачами (CRUD, принаймні створення/видалення/отримування)  
- Керування категоріями  
- Керування записами (за категорією, за користувачем)  
- Автоматично згенерована документація API (Swagger UI)  
- Чиста архітектура: контролери, сервіси, ентіті  
- UUID-ідентифікатори для сутностей

---

## Стек технологій

- Java  
- Spring Boot  
- Maven  
- Spring Web (REST)  
- Spring Data (якщо використовується)  
- springdoc-openapi / Swagger UI  
- Docker

---

## Налаштування і запуск

### Передумови  
- JDK 21
- Maven  

### Запуск  
1. Клонуйте репозиторій:  
   ```bash
   git clone https://github.com/DmytroHalai/lab2-back.git
   cd lab2-back
   ```
Побудуйте і запустіть:

  ```bash
   docker-compose up --build
  ```
Відкрийте браузер і перейдіть до документації API:

```bash
http://localhost:8080/swagger-ui/index.html
```

Використовуйте REST-ендпоінти (наприклад, /users, /user/{id}, /categories, /record, тощо).

Структура проекту
```bash
src/
 ├── main/
 │    ├── java/org/example/lab2back/
 │    │     ├── controller/      # REST-контролери
 │    │     ├── service/         # бізнес-логіка
 │    │     ├── entity/          # сутності (UserEntity, CategoryEntity, RecordEntity)
 │    │     ├── docs/            # інтерфейси документації для контролерів
 │    │     └── config/          # конфігурації (наприклад SwaggerConfig)
 │    └── resources/
 │          └── application.properties
 └── pom.xml
```

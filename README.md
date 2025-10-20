# Lab2-Back

**Lab2-Back** — бекенд-сервіс на базі Spring Boot (Maven) для управління користувачами, категоріями та записами (records).  

Реалізовані REST-ендпоінти: створення, отримання, видалення.  
Документація API — через springdoc‑openapi (Swagger/OpenAPI).

---

Проєкт задеплоєно за допомогою сервісу Render, а також установлено Uptime-Monitor, щоб сервіс не "засинав"

Посилання на задеплоєну документацію проєкту: [посилання](https://cost-accounter.onrender.com/swagger-ui/index.html)

Посилання на колекцію тестування роботи проєкту в Postman: [посилання](https://www.postman.com/gold-eclipse-512795/workspace/cost-accounter/collection/39147312-0a9d68c2-a3da-4b63-a8ff-028e8b35f49e?action=share&source=copy-link&creator=39147312) 
<-- тут також реалізовано тести, які демонструють правильну роботу застосунку, а також реалізовано скрипти для правильного використання UUID, 
яке генерується автоматично, більш схематично можна побачити, використовуючи flow

Посилання на флоу в Postman: [посилання](https://www.postman.com/gold-eclipse-512795/workspace/cost-accounter/flow/68f571be6ca9c00014782e58)

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
- UUID як ідентифікатори сутностей

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
  mvn clean install
  mvn spring-boot:run
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
## API Ендпоінти (короткий огляд)

### Користувачі
GET /users — отримати всіх користувачів

GET /user/{id} — отримати користувача за id

POST /user — створити нового користувача

DELETE /user/{id} — видалити користувача

### Категорії
GET /category — отримати всі категорії

GET /category/{id} — отримати категорію за id

POST /category — створити нову категорію

DELETE /category/{id} — видалити категорію

### Записи (Records)
GET /record — отримати записи, опціонально фільтруючи за userId або categoryId

GET /record/{id} — отримати запис за id

POST /record — створити новий запис

DELETE /record/{id} — видалити запис за id

## Документація API
Після запуску застосунку документація доступна за адресою:

```bash
http://localhost:8080/swagger-ui/index.html
```



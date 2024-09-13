# Guía para Peticiones

## /account/owner/{ownerId}

- **Ejemplo:** `http://localhost:9900/account/owner/{id}`
- **Método:** `GET` - Obtiene cuentas de un usuario
- **Respuestas:**
  - `200 OK`
  - `404 NOT FOUND`
- **ACCEPT:** `JSON/XML`
- **Resultado:**

    ```json
    [
        {
            "id": 1,
            "type": "Personal",
            "balance": 1000,
            "opening_date": "2023-10-01",
            "ownerId": {id}
        }
    ]
    ```

## /account/owner/{id}

- **Ejemplo:** `http://localhost:9900/account/owner/{id}`
- **Método:** `POST` - Crea una cuenta asociada a un usuario
- **Respuestas:**
  - `201 CREATED`
  - `400 BAD REQUEST`
- **ACCEPT:** `JSON/XML`
- **CONTENT-TYPE:** `JSON`
- **BODY:**

    ```json
    {
        "type": "string",
        "balance": "int",
        "opening_date": "YYYY-MM-DD"
    }
    ```

## /account/owner/{id}

- **Ejemplo:** `http://localhost:9900/account/owner/{id}`
- **Método:** `DELETE` - Elimina todas las cuentas de un usuario
- **Respuestas:**
  - `204 NO CONTENT`
  - `400 BAD REQUEST`

## /account/{accountId}

- **Ejemplo:** `http://localhost:9900/account/{id}`
- **Método:** `DELETE` - Elimina una cuenta
- **Respuestas:**
  - `204 NO CONTENT`
  - `400 BAD REQUEST`

## /account/{accountId}

- **Ejemplo:** `http://localhost:9900/account/{id}`
- **Método:** `PUT` - Modifica una cuenta de un usuario
- **Respuestas:**
  - `202 ACCEPTED`
  - `400 BAD REQUEST`
- **ACCEPT:** `JSON/XML`
- **CONTENT-TYPE:** `JSON`
- **BODY:**

    ```json
    {
        "balance": "int"
    }
    ```

## /account/add

- **Ejemplo:** `http://localhost:9900/account/add`
- **Método:** `PUT` - Añadir balance a una cuenta de un usuario
- **Respuestas:**
  - `202 ACCEPTED`
  - `400 BAD REQUEST`
- **ACCEPT:** `JSON/XML`
- **CONTENT-TYPE:** `JSON`
- **BODY:**

    ```json
    {
        "idCuenta": "int",
        "idPropietario": "int",
        "dinero": "int"
    }
    ```

## /account/withdraw

- **Ejemplo:** `http://localhost:9900/account/withdraw`
- **Método:** `PUT` - Retirar balance de una cuenta de un usuario
- **Respuestas:**
  - `202 ACCEPTED`
  - `400 BAD REQUEST`
- **ACCEPT:** `JSON/XML`
- **CONTENT-TYPE:** `JSON`
- **BODY:**

    ```json
    {
        "idCuenta": "int",
        "idPropietario": "int",
        "dinero": "int"
    }
    ```

## /account/owner/{id}/prestamo/{cantidad}

- **Ejemplo:** `http://localhost:9900/account/owner/{id}/prestamo/{cantidad}`
- **Método:** `GET` - Comprobar si el préstamo es válido
- **Respuestas:**
  - `200 OK`
  - `400 BAD REQUEST`
- **ACCEPT:** `JSON/XML`
- **CONTENT-TYPE:** `JSON`
- **Resultado:** `Es válido || No válido`

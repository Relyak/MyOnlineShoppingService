   <h1>Guía para Peticiones</h1>
    <div>
        <h2>/account/owner/{ownerId}</h2>
        <p>Ejemplo: <code>http://localhost:9900/account/owner/{id}</code></p>
        <p><strong>GET:</strong> Obtiene cuentas de un usuario</p>
        <p><strong>Respuestas:</strong></p>
        <ul>
            <li>200 -> OK</li>
            <li>404 -> NOT FOUND</li>
        </ul>
        <p><strong>ACCEPT:</strong> {JSON/XML}</p>
        <p><strong>Resultado:</strong></p>
        <pre>
[
    {
        "id": 1,
        "type": "Personal",
        "balance": 1000,
        "opening_date": "2023-10-01",
        "ownerId": {id}
    }
]
        </pre>
    </div>
    
  <div>
        <h2>/account/owner/{id}</h2>
        <p>Ejemplo: <code>http://localhost:9900/account/owner/{id}</code></p>
        <p><strong>POST:</strong> Crea una cuenta asociada a un usuario</p>
        <p><strong>Respuestas:</strong></p>
        <ul>
            <li>201 -> CREATED</li>
            <li>400 -> BAD REQUEST</li>
        </ul>
        <p><strong>ACCEPT:</strong> {JSON/XML}</p>
        <p><strong>CONTENT-TYPE:</strong> {JSON}</p>
        <p><strong>BODY:</strong></p>
        <pre>
{
    "type": {string},
    "balance": {int},
    "opening_date": {YYYY-MM-DD}
}
        </pre>
  </div>
    
    <div>
        <h2>/account/owner/{id}</h2>
        <p>Ejemplo: <code>http://localhost:9900/account/owner/{id}</code></p>
        <p><strong>DELETE:</strong> Elimina todas las cuentas de un usuario</p>
        <p><strong>Respuestas:</strong></p>
        <ul>
            <li>204 -> NO CONTENT</li>
            <li>400 -> BAD REQUEST</li>
        </ul>
    </div>

    <div>
        <h2>/account/{accountId}</h2>
        <p>Ejemplo: <code>http://localhost:9900/account/{id}</code></p>
        <p><strong>DELETE:</strong> Elimina una cuenta</p>
        <p><strong>Respuestas:</strong></p>
        <ul>
            <li>204 -> NO CONTENT</li>
            <li>400 -> BAD REQUEST</li>
        </ul>
    </div>

    <div>
        <h2>/account/{accountId}</h2>
        <p>Ejemplo: <code>http://localhost:9900/account/{id}</code></p>
        <p><strong>PUT:</strong> Modifica una cuenta de un usuario</p>
        <p><strong>Respuestas:</strong></p>
        <ul>
            <li>202 -> ACCEPTED</li>
            <li>400 -> BAD REQUEST</li>
        </ul>
        <p><strong>ACCEPT:</strong> {JSON/XML}</p>
        <p><strong>CONTENT-TYPE:</strong> {JSON}</p>
        <p><strong>BODY:</strong></p>
        <pre>
{
    "balance": {int}
}
        </pre>
    </div>

    <div>
        <h2>/account/account/add</h2>
        <p>Ejemplo: <code>http://localhost:9900/account/add</code></p>
        <p><strong>PUT:</strong> Añadir balance a una cuenta de un usuario</p>
        <p><strong>Respuestas:</strong></p>
        <ul>
            <li>202 -> ACCEPTED</li>
            <li>400 -> BAD REQUEST</li>
        </ul>
        <p><strong>ACCEPT:</strong> {JSON/XML}</p>
        <p><strong>CONTENT-TYPE:</strong> {JSON}</p>
        <p><strong>BODY:</strong></p>
        <pre>
{
    "idCuenta": {int},
    "idPropietario": {int},
    "dinero": {int}
}
        </pre>
    </div>

    <div>
        <h2>/account/account/withdraw</h2>
        <p>Ejemplo: <code>http://localhost:9900/account/withdraw</code></p>
        <p><strong>PUT:</strong> Retirar balance de una cuenta de un usuario</p>
        <p><strong>Respuestas:</strong></p>
        <ul>
            <li>202 -> ACCEPTED</li>
            <li>400 -> BAD REQUEST</li>
        </ul>
        <p><strong>ACCEPT:</strong> {JSON/XML}</p>
        <p><strong>CONTENT-TYPE:</strong> {JSON}</p>
        <p><strong>BODY:</strong></p>
        <pre>
{
    "idCuenta": {int},
    "idPropietario": {int},
    "dinero": {int}
}
        </pre>
    </div>

    <div>
        <h2>/account/owner/{id}/prestamo/{cantidad}</h2>
        <p>Ejemplo: <code>http://localhost:9900/account/owner/{id}/prestamo/{cantidad}</code></p>
        <p><strong>GET:</strong> Comprobar si el préstamo es válido</p>
        <p><strong>Respuestas:</strong></p>
        <ul>
            <li>200 -> OK</li>
            <li>400 -> BAD REQUEST</li>
        </ul>
        <p><strong>ACCEPT:</strong> {JSON/XML}</p>
        <p><strong>CONTENT-TYPE:</strong> {JSON}</p>
        <p><strong>Resultado:</strong> Es válido || No válido</p>
    </div>

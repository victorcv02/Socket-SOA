import socket

def servidor(host='127.0.0.1', port=12345):
    # Crear el socket (IPv4, TCP)
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as servidor_socket:
        servidor_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        servidor_socket.bind((host, port))            # Vincular IP/puerto
        servidor_socket.listen(5)                     # Escuchar hasta 5 en cola
        print(f"Servidor escuchando en {host}:{port} ...")

        while True:
            # Aceptar una conexión entrante (bloquea hasta que llegue un cliente)
            cliente_socket, direccion_cliente = servidor_socket.accept()
            with cliente_socket:
                print(f"Conexión establecida con: {direccion_cliente}")

                # Enviar un mensaje al cliente
                mensaje = "¡Hola desde el servidor!"
                cliente_socket.sendall(mensaje.encode('utf-8'))

                # Recibir datos del cliente (máx 1024 bytes)
                datos = cliente_socket.recv(1024)
                if not datos:
                    print("Cliente cerró la conexión.")
                    continue

                print(f"Mensaje del cliente: {datos.decode('utf-8')}")

if __name__ == "__main__":
    servidor()

import socket

def cliente(host='127.0.0.1', port=12345):
    # Crear el socket (IPv4, TCP)
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as cliente_socket:
        # Conectar al servidor
        cliente_socket.connect((host, port))

        # Recibir el mensaje del servidor
        mensaje_servidor = cliente_socket.recv(1024)
        print(f"Mensaje del servidor: {mensaje_servidor.decode('utf-8')}")

        # Enviar un mensaje al servidor
        mensaje_cliente = "¡Hola servidor!"
        cliente_socket.sendall(mensaje_cliente.encode('utf-8'))

if __name__ == "__main__":
    cliente()

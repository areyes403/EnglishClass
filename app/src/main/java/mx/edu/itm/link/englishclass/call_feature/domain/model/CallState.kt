package mx.edu.itm.link.englishclass.call_feature.domain.model

enum class CallState {
    CONNECTING, // Conectando
    CONNECTED, // Conectado
    DISCONNECTED, // Desconectado
    FAILED // Fallo en la conexión
}
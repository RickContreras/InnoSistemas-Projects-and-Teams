#!/bin/bash

# Script para iniciar la aplicación en Codespaces
# Detecta automáticamente la URL de Codespaces y configura Swagger

echo "🚀 Iniciando InnoSistemas API..."
echo ""

# Puerto por defecto de Spring Boot
PORT=${SERVER_PORT:-8080}

# Detectar si estamos en Codespaces
if [ -n "$CODESPACE_NAME" ] && [ -n "$GITHUB_CODESPACES_PORT_FORWARDING_DOMAIN" ]; then
    echo "✅ Detectado entorno GitHub Codespaces"
    echo "📦 Codespace: $CODESPACE_NAME"
    
    # Construir URLs de Codespaces
    BASE_URL="https://${CODESPACE_NAME}-${PORT}.${GITHUB_CODESPACES_PORT_FORWARDING_DOMAIN}"
    SWAGGER_URL="${BASE_URL}/swagger-ui.html"
    API_DOCS_URL="${BASE_URL}/api-docs"
    
    echo ""
    echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
    echo "📚 URLs de Swagger:"
    echo "   Swagger UI: ${SWAGGER_URL}"
    echo "   API Docs:   ${API_DOCS_URL}"
    echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
    echo ""
else
    echo "💻 Ejecutando en entorno local"
    
    SWAGGER_URL="http://localhost:${PORT}/swagger-ui.html"
    API_DOCS_URL="http://localhost:${PORT}/api-docs"
    
    echo ""
    echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
    echo "📚 URLs de Swagger:"
    echo "   Swagger UI: ${SWAGGER_URL}"
    echo "   API Docs:   ${API_DOCS_URL}"
    echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
    echo ""
fi

# Limpiar target anterior
echo "🧹 Limpiando compilaciones anteriores..."
./mvnw clean -q

# Compilar el proyecto
echo "🔨 Compilando proyecto..."
./mvnw compile -DskipTests

if [ $? -ne 0 ]; then
    echo ""
    echo "❌ Error en la compilación. Por favor revisa los errores anteriores."
    exit 1
fi

echo ""
echo "✅ Compilación exitosa"
echo "🚀 Iniciando aplicación en puerto $PORT..."
echo ""
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo "⏳ Espera a que la aplicación inicie completamente..."
echo "   Cuando veas 'Started InnoSistemasApplication', accede a:"
if [ -n "$CODESPACE_NAME" ]; then
    echo ""
    echo "   🌐 ${SWAGGER_URL}"
    echo ""
    echo "   VS Code también mostrará una notificación con el link."
else
    echo "   🌐 ${SWAGGER_URL}"
fi
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo ""

# Ejecutar la aplicación
./mvnw spring-boot:run

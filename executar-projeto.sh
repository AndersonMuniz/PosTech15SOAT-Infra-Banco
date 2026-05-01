#!/usr/bin/env bash
set -euo pipefail

if ! command -v docker >/dev/null 2>&1; then
  echo "Docker nao encontrado. Instale o Docker antes de executar o projeto."
  exit 1
fi

if ! docker compose version >/dev/null 2>&1; then
  echo "Docker Compose nao encontrado. Instale o plugin 'docker compose' antes de executar o projeto."
  exit 1
fi

echo "Subindo NumberOne com Docker Compose..."
docker compose up --build

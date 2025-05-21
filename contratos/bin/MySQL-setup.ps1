# Verifica se o comando 'mysql' está disponível
if (Get-Command mysql -ErrorAction SilentlyContinue) {
    Write-Host "O comando 'mysql' já está disponível no sistema."
    & ".\DATABASE-setup.ps1"
    exit
}

# Caminho padrão de instalação do MySQL
$mysqlPath = "C:\Program Files\MySQL\MySQL Server 9.2\bin"

if (Test-Path $mysqlPath) {
    # Verifica se o MySQL está instalado
    $mysqlCommand = Join-Path $mysqlPath "mysql.exe"
    if (Test-Path $mysqlCommand) {
        Write-Host "O MySQL está instalado no caminho $mysqlPath"
    } else {
        Write-Host "O MySQL não foi encontrado no caminho $mysqlPath"
        exit
    }
    
    # Verifica se já está no PATH do usuário
    $currentPath = [Environment]::GetEnvironmentVariable("Path", "User")
    if ($currentPath -like "*$mysqlPath*") {
        Write-Host "O caminho já está presente no PATH do usuário."
    }
    # Adiciona ao PATH do usuário
    else {
        $newPath = "$currentPath;$mysqlPath"
        [Environment]::SetEnvironmentVariable("Path", $newPath, "User")
        Write-Host "Caminho '$mysqlPath' adicionado ao PATH do usuário com sucesso."
    }
    Write-Host "Você pode precisar reiniciar o PowerShell ou fazer logoff/login para aplicar as mudanças."
    & ".\bin\DATABASE-setup.ps1"
    exit
} else {
    Write-Host "O caminho '$mysqlPath' não existe. Verifique se o MySQL está instalado corretamente."
    exit
}
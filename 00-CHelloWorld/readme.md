
Resolución 00-CHelloWorld

Datos del Compilador
- Compilador seleccionado:** GCC 
- Versión del compilador:** 14.2.0 
- Versión de C:** C23 

Procedimiento
1. Se redactó el código en `HolaMundo.c` utilizando `printf`.
2. Se compiló mediante la línea de comandos con el flag `-std=c23`.
3. Se generó el archivo `output.txt` mediante redirección de flujo: `Hola.exe > output.txt`.

Comprobación del estándar
Al compilar con `-std=c23`, la macro `__STDC_VERSION__` arroja el valor `202000L` , lo que confirma el soporte para C23.

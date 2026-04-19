#include <stdio.h> 


int main (void) {
    
printf("Hola, Mundo!\n\n");


#ifdef __STDC_VERSION__
        printf("Version de C: %ld\n", __STDC_VERSION__);
    #endif

return 0;

}
#include <stdbool.h>
#include <assert.h>
#include <pthread.h>
#include "int-pipe2.h"



bool int_pipe_empty(int_pipe_t *intp) {
  return bounded_buffer_empty(&intp->buf);
}

bool int_pipe_full(int_pipe_t *intp) {
  return bounded_buffer_full(&intp->buf);
}

void int_pipe_init(int_pipe_t *intp) {
  bounded_buffer_init(&intp->buf);
  intp->closed = false;
  pthread_mutex_init(&intp->mutex, NULL);
}

int int_pipe_size(int_pipe_t *intp) {
  return bounded_buffer_size(&intp->buf);
}

void int_pipe_close(int_pipe_t *intp) {
  intp->closed = true;
}

bool int_pipe_closed(int_pipe_t *intp) {
  return intp->closed;
}


int int_pipe_write(int_pipe_t *intp, int n) {
  while(int_pipe_full(intp) && !int_pipe_closed(intp)) { 
      /*Busy waiting. Need to fix this! */ }
  if(int_pipe_closed(intp)) return 0;
  pthread_mutex_lock(&intp->mutex);
  assert(!int_pipe_full(intp) && !int_pipe_closed(intp)); 
  bounded_buffer_add(&intp->buf,n);
  pthread_mutex_unlock(&intp->mutex);
  return 1;
}

int int_pipe_read(int_pipe_t *intp) {
  while(int_pipe_empty(intp) && !int_pipe_closed(intp)) { 
     /* Busy waiting. Need to fix this! */ }
  if(int_pipe_closed(intp)) return 0;
  pthread_mutex_lock(&intp->mutex);
  assert(!int_pipe_empty(intp) && !int_pipe_closed(intp)); 
  int n = bounded_buffer_remove(&intp->buf);
  pthread_mutex_unlock(&intp->mutex);
  return n;
}


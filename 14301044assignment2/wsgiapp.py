import sys, os

def app(environ, start_response):
	"""A barebones WSGI application.
	This is a starting point for your own Web framework :)
	"""
	
	full_path = os.getcwd() + environ.get('PATH_INFO')
	num=len(environ.get('PATH_INFO').split('.'))
	if not os.path.exists(full_path) and num > 1:
		return not_found(environ,start_response)
	elif os.path.isfile(full_path):
		return handle_file(environ, start_response)
	elif len(environ.get('PATH_INFO')) == 1:
		return hello(environ, start_response)
	else:
		return hello_name(environ, start_response)
		
def hello(environ, start_response):
	start_response('200 OK', [('Content-Type', 'text/html')])
	return ['hello world']
  
def hello_name(environ, start_response):
	start_response('200 OK', [('Content-Type', 'text/html')])
	return ['hello {name}'.format(name=environ.get('PATH_INFO')[1:])]
 

def not_found(environ, start_response):
	start_response('404 NOT FOUND', [('Content-Type', 'text/plain')])
	return ['404 Not Found']
  
def handle_file(environ, start_response):
		
		full_path = os.getcwd() + environ.get('PATH_INFO')
		with open(full_path, 'rb') as reader:
			content = reader.read()
		start_response('200 OK', [('Content-Type', 'text/html')])
		return [content]
		
			

import tornado.ioloop, tornado.web, os, sys, logging

root = os.path.join(os.path.dirname(__file__), ".")

class MainHandler(tornado.web.RequestHandler):
    def get(self):
        self.render("index.html")

if __name__ == "__main__":
    app = tornado.web.Application([
        (r"/", MainHandler),
        (r"/static/(.*)", tornado.web.StaticFileHandler, {'path': os.path.join(root, 'static')})
    ], template_path="./views", debug=True)

    app.listen(80)
    tornado.ioloop.IOLoop.current().start()

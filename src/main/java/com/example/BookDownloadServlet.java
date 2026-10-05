package com.example;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/book")
public class BookDownloadServlet extends HttpServlet {

    private static final String BOOKS_DIR = System.getProperty("java.io.tmpdir") + "/books";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        File file = new File(BOOKS_DIR, new File(name).getName());

        if (name == null || !file.isFile()) {
            resp.setContentType("text/plain;charset=UTF-8");
            resp.getWriter().println("Книга не найдена");
            return;
        }

        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

        try (InputStream in = new FileInputStream(file);
             OutputStream out = resp.getOutputStream()) {
            in.transferTo(out);
        }
    }
}
package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/load-book")
@MultipartConfig
public class BookUploadServlet extends HttpServlet {

    private static final String BOOKS_DIR = System.getProperty("java.io.tmpdir") + "/books";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/plain;charset=UTF-8");

        File dir = new File(BOOKS_DIR);
        dir.mkdirs();

        Part part = req.getPart("file");
        String name = new File(part.getSubmittedFileName()).getName();
        File target = new File(dir, name);

        try (InputStream in = part.getInputStream()) {
            java.nio.file.Files.copy(in, target.toPath(),
                    java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        }

        resp.getWriter().println("Загружено: " + name);
    }
}

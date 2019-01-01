package com.ircfront.Utils;

import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepository;
import org.gitective.core.CommitUtils;

import java.io.IOException;

public class GitFinder {
  public static String findCommit(){
    try {
      Repository repo = new FileRepository(".git");
      RevCommit latestCommit = CommitUtils.getHead(repo);
      return latestCommit.name();
    } catch (IOException e) {
      e.printStackTrace();
      return "";
    }
  }
}
